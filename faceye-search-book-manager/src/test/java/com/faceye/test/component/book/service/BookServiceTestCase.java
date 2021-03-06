package com.faceye.test.component.book.service;

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

import com.faceye.component.book.doc.Book;
import com.faceye.component.book.service.BookService;
import com.faceye.test.feature.service.BaseServiceTestCase;


/**
 * Book  服务层测试用例
 * 
 * @author @haipenge haipenge@gmail.com Create Date:2014年5月20日
 */
public class BookServiceTestCase extends BaseServiceTestCase {
	@Autowired
	private BookService bookService = null;
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
		Assert.isTrue(bookService != null);
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
//		this.bookService.removeAllInBatch();
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
		Book entity = new Book();
		this.bookService.save(entity);
		List<Book> entites = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testSaveAndFlush() throws Exception {
		Book entity = new Book();
		this.bookService.save(entity);
		List<Book> entites = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entites));
	}

	@Test
	public void testMultiSave() throws Exception {
		for (int i = 0; i < 5; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
		}
		List<Book> entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testRemoveById() throws Exception {
		Book entity = new Book();
		this.bookService.save(entity);
		logger.debug(">>Entity id is:" + entity.getId());
		Book e = this.bookService.get(entity.getId());
		Assert.isTrue(e != null);
	}

	@Test
	public void testRemoveEntity() throws Exception {
		Book entity = new Book();
		this.bookService.save(entity);
		this.bookService.remove(entity);
		List<Book> entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAllInBatch() throws Exception {
		for (int i = 0; i < 5; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
		}
		List<Book> entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
		this.bookService.removeAllInBatch();
		entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
		}
		this.bookService.removeAll();
		List<Book> entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testRemoveListInBatch() throws Exception {
		List<Book> entities = new ArrayList<Book>();
		for (int i = 0; i < 5; i++) {
			Book entity = new Book();
			
			this.bookService.save(entity);
			entities.add(entity);
		}
		this.bookService.removeInBatch(entities);
		entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isEmpty(entities));
	}

	@Test
	public void testGetAll() throws Exception {
		for (int i = 0; i < 5; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
		}
		List<Book> entities = this.bookService.getAll();
		Assert.isTrue(CollectionUtils.isNotEmpty(entities) && entities.size() == 5);
	}

	@Test
	public void testGetPage() throws Exception {
		for (int i = 0; i < 25; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
		}
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<Book> page = this.bookService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getSize() == 5);
		searchParams.put("EQ_name", "test-10");
		page = this.bookService.getPage(searchParams, 1, 5);
		Assert.isTrue(page != null && page.getTotalElements() == 1);
		searchParams = new HashMap<String, Object>();
		searchParams.put("LIKE_name", "test");
		page = this.bookService.getPage(searchParams, 1, 5);

		Assert.isTrue(page != null && page.getTotalElements() == 25 && page.getNumberOfElements() == 5);

	}

	@Test
	public void testGet() throws Exception {
		Long id = null;
		for (int i = 0; i < 25; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
			id = entity.getId();
		}
		Book e = this.bookService.get(id);
		Assert.isTrue(e != null);
	}

	@Test
	public void testGetByIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < 25; i++) {
			Book entity = new Book();
			this.bookService.save(entity);
			if (i < 5) {
				ids.add(entity.getId());
			}
		}
		List<Book> entities = this.bookService.getAll(ids);
		Assert.isTrue(entities != null && entities.size() == 5);
	}
}
