package kr.co.sunnyvale.guestbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import kr.co.sunnyvale.guestbook.domain.Guestbook;
import kr.co.sunnyvale.guestbook.domain.Image;
import kr.co.sunnyvale.guestbook.domain.QImage;
import kr.co.sunnyvale.guestbook.domain.User;
import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.repository.GuestbookRepository;
import kr.co.sunnyvale.guestbook.repository.ImageRepository;
import kr.co.sunnyvale.guestbook.repository.UserRepository;
import kr.co.sunnyvale.guestbook.service.GuestbookService;
import kr.co.sunnyvale.security.AuthUser;
import kr.co.sunnyvale.security.SecurityLoginInfoDTO;
import kr.co.sunnyvale.support.util.CalendarUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/guestbook")
@Transactional
public class GuestbookController {
	@Autowired GuestbookService guestbookService;
	@Autowired
	private GuestbookRepository guestbookRepository;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired UserRepository userRepository;
	
	
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public String defaultlist(Model model) throws Exception {
		return "forward:list/1";
		
	}
	
	@RequestMapping(value="/list/{pageNumber}", method={RequestMethod.GET})
	public String list(@PathVariable("pageNumber")int pageNumber, Model model) throws Exception {
		
		// 페이징 처리. 
		// http://springinpractice.com/2012/05/11/pagination-and-sorting-with-spring-data-jpa
		Page<Guestbook> page = guestbookService.getGuestbookList(pageNumber);

	    int current = page.getNumber() + 1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10 -1, page.getTotalPages());

	    model.addAttribute("pageData", page);
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
		return "guestbook/list";
		
	}
	
	@RequestMapping(value="/download/{id}", method={RequestMethod.GET})
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") long id, Model model) throws Exception {
		QImage qi = QImage.image;
		Image image = imageRepository.findOne(qi.id.eq(id));
		
		File f = new File(image.getRealPath());
		if(!f.exists()){
			String fnfImage  = request.getSession().getServletContext().getRealPath("/images/fnf.jpg");
			f = new File(fnfImage);
			response.setContentType("image/jpeg");
			response.setContentLength((int)f.length());
		}else{
	        if (image.getContentType() == null) {
	            // set to binary type if MIME mapping not found
	            image.setContentType("application/octet-stream");
	        }
	        response.setContentType(image.getContentType());
	        response.setContentLength((int) image.getFileLength());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	        		image.getFileName());
	        response.setHeader(headerKey, headerValue);
		}
        // get output stream of the response
        
        FileInputStream inputStream = new FileInputStream(f);
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
		
	}	

	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(@ModelAttribute("guestbook") GuestbookDTO guestbook, @AuthUser SecurityLoginInfoDTO loginInfo, Model model) throws Exception {
		return "guestbook/writeform";
	}


	@RequestMapping(value="/write", method=RequestMethod.POST)
	@Transactional
	public String write(HttpServletRequest request, @AuthUser SecurityLoginInfoDTO loginInfo, @ModelAttribute("guestbook") @Valid GuestbookDTO guestbookDTO, BindingResult bindingResult , Model model) {
		if(bindingResult.hasErrors()){
			return "guestbook/writeform";
		}
		
		Guestbook guestbook = new Guestbook();
		guestbook.setContent(guestbookDTO.getContent());
		
		User user = userRepository.findByUserId(loginInfo.getUserId());
		guestbook.setUser(user);

		
		System.out.println("guestbookDTO write : " + guestbookDTO);

		// 업로드를 하든 안하든 3번 반복함
		MultipartFile images[] = guestbookDTO.getImages();
		if(images.length > 0){
			String saveDir = CalendarUtil.getTodayYyyyMmDd(); //   /2014/11/13 문자열 구함.
			// eclipse에서 실행할 경우엔 디플로이되는 경로가 자주 지위지니 특정 폴더를 사용한다.
			//String realPath  = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			String realPath = "c:/temp";
			String separator = File.separator; // windows : \      mac, linux, unix : /
			
			if("\\".equals(separator)){
				saveDir = saveDir.replace("/", "\\");
				//System.out.println(prefix);
			}
			
			saveDir = realPath + saveDir + separator;
			File saveDirFile = new File(saveDir);
			if(!saveDirFile.exists()){ // 디렉토리가 존재하지 않는다면.
				saveDirFile.mkdirs(); // 디렉토리를 생성한다.
			}
			
			System.out.println("-------------------------------------------------");
			//System.out.println("realPath : " + saveDir);
			for(int i = 0; i < images.length; i++){

				MultipartFile mf = images[i];
				System.out.println(mf.getOriginalFilename());
				System.out.println(mf.getName());
				System.out.println(mf.getContentType());
				System.out.println(mf.getSize());
				System.out.println("-------------------------------------------------");
				
				// 파일이 존재할 경우
				if(mf.getOriginalFilename() != null && !"".equals(mf.getOriginalFilename())){
					Image image = new Image();
					image.setFileLength(mf.getSize());
					image.setFileName(mf.getOriginalFilename());
					String saveFileName = mf.getOriginalFilename() + "_" + System.nanoTime() + "_" + Thread.currentThread().getId();
					String saveRealPath = saveDir + saveFileName;
					try {
						mf.transferTo(new File(saveRealPath));
					} catch (Exception e) {
						throw new RuntimeException("file save error : " + saveFileName + " : " + e);
					}
					image.setContentType(mf.getContentType());
					image.setRealPath(saveRealPath);
					System.out.println("saveRealPath : " + saveRealPath);
					image.setSaveFileName(saveFileName);
					
					System.out.println("image :" + image);
					guestbook.addImage(image);
				
				}
			}
			guestbook = guestbookRepository.save(guestbook);
//			Transaction test			
//			if(0 == 0)
//				throw new RuntimeException("ex!!");			
		}
		
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value="/list.api", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Guestbook> list() throws Exception {

		List<Guestbook> list = guestbookRepository.findAll();
		return list;
	}
	
	@RequestMapping(value="/delete/{id}", method={RequestMethod.GET,RequestMethod.POST})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public boolean delete(@PathVariable long id) throws Exception {
		Guestbook guestbook = guestbookRepository.findOne(id);
		if(guestbook == null)
			return false;
		guestbookRepository.delete(guestbook);
		return true;
	}	
}