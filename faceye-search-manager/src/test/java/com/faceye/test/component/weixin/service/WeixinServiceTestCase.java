package com.faceye.test.component.weixin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import com.faceye.component.weixin.doc.Weixin;
import com.faceye.component.weixin.service.WeixinService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Weixin  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class WeixinServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private WeixinService weixinService = null;
	/**
	 * 初始化
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Before
	public void set() throws Exception {
		Assert.isTrue(weixinService != null);
	}

	/**
	 * 清理
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@After
	public void after() throws Exception {
		//this.weixinService.removeAllInBatch();
	}

	/**
	 *  保存测试
	 * @todo
	 * @throws Exception
	 * @author:@haipenge
	 * haipenge@gmail.com
	 * 2014年5月20日
	 */
	@Test
	public void testSave() throws Exception {
		Weixin entity = new Weixin();
		this.weixinService.save(entity);
		List<Weixin> entites = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Weixin entity = new Weixin();
		this.weixinService.save(entity);
		List<Weixin> entites = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
		}
		List<Weixin> entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Weixin entity = new Weixin();
		this.weixinService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Weixin e = this.weixinService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Weixin entity = new Weixin();
		this.weixinService.save(entity);
		this.weixinService.remove(entity);
		List<Weixin> entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
		}
		List<Weixin> entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.weixinService.removeAllInBatch();
		entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
		}
		this.weixinService.removeAll();
		List<Weixin> entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Weixin> entities = new ArrayList<Weixin>();
		for (int i = 0; i < 5; i++) {
			Weixin entity = new Weixin();
			
			this.weixinService.save(entity);
			entities.add(entity);
		}
		this.weixinService.removeInBatch(entities);
		entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
		}
		List<Weixin> entities = this.weixinService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Weixin> page = this.weixinService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.weixinService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.weixinService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
			id = entity.getId();
		}
		Weixin e = this.weixinService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Weixin entity = new Weixin();
			this.weixinService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Weixin> entities = this.weixinService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
