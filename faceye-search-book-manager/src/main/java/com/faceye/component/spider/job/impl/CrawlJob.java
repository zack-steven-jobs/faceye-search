package com.faceye.component.spider.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faceye.component.spider.service.CrawlService;
import com.faceye.component.spider.service.SiteLinkService;
import com.faceye.feature.util.ServiceException;

/**
 * 网页爬取JOB
 * @author @haipenge 
 * haipenge@gmail.com
*  Create Date:2014年7月7日
 */
@Service
public class CrawlJob extends BaseJob {
	private Boolean isJobRun = Boolean.FALSE;
	@Autowired
	private CrawlService crawlService = null;

	@Override
	public void run() throws ServiceException {
		if (!isJobRun) {
			try {
				isJobRun=Boolean.TRUE;
				crawlService.crawl();
			} catch (Exception e) {
				logger.error(">>E:", e);
			} finally {
				isJobRun = Boolean.FALSE;
			}
		}
	}

}
