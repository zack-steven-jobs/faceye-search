package com.faceye.component.spider.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.parse.service.ParseResultService;
import com.faceye.feature.util.ServiceException;

@Service
public class ResetCrawlResultJob extends BaseJob {

	@Autowired
	private ParseResultService parseResultService = null;

	@Override
	public void run() throws ServiceException {
		this.parseResultService.resetParseResult();
	}

}
