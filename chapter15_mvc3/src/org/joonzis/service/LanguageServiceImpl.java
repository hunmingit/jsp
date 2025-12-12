package org.joonzis.service;

public class LanguageServiceImpl implements LanguageService{
	@Override
	public String executeEnglish() {
		return "hello";
	}
	@Override
	public String executeHangeul() {
		return "안녕";
	}
}
