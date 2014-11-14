package kr.co.sunnyvale.guestbook.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import kr.co.sunnyvale.commons.util.CalendarUtil;
import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.dto.ImageDTO;
import kr.co.sunnyvale.guestbook.service.GuestbookService;
import kr.co.sunnyvale.security.AuthUser;
import kr.co.sunnyvale.security.SecurityLoginInfoDTO;
import kr.co.sunnyvale.security.SecurityUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list", method={RequestMethod.GET})
	public String list(Model model) throws Exception {
		List<GuestbookDTO> list = guestbookService.getList();
		model.addAttribute("list", list);

		return "guestbook/list";
		
	}
	
	@RequestMapping(value="/download/{seq}", method={RequestMethod.GET})
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("seq") int seq, Model model) throws Exception {
		ImageDTO image = guestbookService.getImage(seq);
		
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
	public String write(@ModelAttribute("guestbook") GuestbookDTO guestbook, @AuthUser SecurityUserDTO securityUser, Model model) throws Exception {
		return "guestbook/writeform";
	}


	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(HttpServletRequest request, @AuthUser SecurityLoginInfoDTO loginInfo, @RequestParam("content") String content,
			@RequestParam("images") MultipartFile images[], Model model) {
		
		GuestbookDTO guestbook = new GuestbookDTO();
		guestbook.setContent(content);
		guestbook.setName(loginInfo.getName());
		System.out.println("guestbook write : " + guestbook);
//		
		List<ImageDTO> imageList = new ArrayList<ImageDTO>();
		// 업로드를 하든 안하든 3번 반복함.
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
					ImageDTO imageDTO = new ImageDTO();
					imageDTO.setFileLength(mf.getSize());
					imageDTO.setFileName(mf.getOriginalFilename());
					String saveFileName = mf.getOriginalFilename() + "_" + System.nanoTime() + "_" + Thread.currentThread().getId();
					String saveRealPath = saveDir + saveFileName;
					try {
						mf.transferTo(new File(saveRealPath));
					} catch (Exception e) {
						throw new RuntimeException("file save error : " + saveFileName + " : " + e);
					}
					imageDTO.setContentType(mf.getContentType());
					imageDTO.setRealPath(saveRealPath);
					System.out.println("saveRealPath : " + saveRealPath);
					imageDTO.setSaveFileName(saveFileName);
					imageList.add(imageDTO);
				}
			}
		}
		guestbookService.addGuestbook(guestbook, imageList);
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value="/list.api", method={RequestMethod.GET})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<GuestbookDTO> list() throws Exception {

		List<GuestbookDTO> list = guestbookService.getList();
		return list;
	}
}