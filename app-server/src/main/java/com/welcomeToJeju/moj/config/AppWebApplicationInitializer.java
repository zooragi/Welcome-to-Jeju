package com.welcomeToJeju.moj.config;

import java.io.File;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	// loader 설정인가??
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {RootConfig.class};
	}
	
	// 페이지 컨트롤러 설정인가??
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		// DispatcherServlet에 대해 URL 매핑 정보를 리턴한다.
		return new String[] {"/app/*"};
	}
	
	@Override
	protected String getServletName() {
		// DispatcherServlet의 이름을 리턴한다.
		return "app";
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("UTF-8")};
	}
	
	@Override
  protected void customizeRegistration(
      javax.servlet.ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(
				new MultipartConfigElement(
						new File(System.getProperty("java.io.tmpdir")).getAbsolutePath(), // 업로드 한 파일을 임시 보관할 위치
						10000000, // 최대 업로드할 수 있는 파일들의 총 크기
						15000000, // 요청 전체 데이터의 크기
						2000000 // 업로드 되고 있는 파일을 메모리에 임시 임시 보관하는 크기
						));
	  }
	
}








