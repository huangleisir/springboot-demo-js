package com.jst.framework.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 
 * 
 * @Package: com.tomtop.framework.core.redis
 * @ClassName: BaseRedisTemplate
 * @Description: Redis基础模版处理基类,提供操作value为string类型基础操作方法，以及基础key操作方法
 *
 * @author: lixin
 * @date: 2016年9月8日 下午6:20:59
 * @version V1.0
 */
public class BaseRedisTemplate extends StringRedisTemplate implements
		ICacheTemplate {

	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;

	/**
	 * 设置单个值,如果有原值不更新
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public void set(String key, String value) {

		String oldValue = opsForValue().get(key);

		if (oldValue != null) {
			return;
		}

		opsForValue().set(key, value);
	}

	/**
	 * 设置某个值，同时设置该key的超时时间
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            超时时间以秒为单位
	 * @return
	 */
	public void setex(String key, String value, long timeout) {

		String oldValue = opsForValue().get(key);

		if (oldValue != null) {
			return;
		}

		opsForValue().set(key, value, timeout, TimeUnit.SECONDS);

	}

	/**
	 * 设置某个值，同时设置该key的超时时间
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            超时时间以TimeUnit设置时间周期为单位
	 * @return
	 */
	public void setex(String key, String value, long timeout, TimeUnit unit) {

		String oldValue = opsForValue().get(key);

		if (oldValue != null) {
			return;
		}

		opsForValue().set(key, value, timeout, unit);

	}

	/**
	 * 设置单个值,如果有原值直接覆盖原值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean update(String key, String value) {

		if (value == null || "".equals(value)) {
			return false;
		}

		opsForValue().set(key, value);

		/** 如果缓存中被更新的值与当前原值匹配，返回更新成功,否则失败 */
		if (!value.equals(opsForValue().get(key))) {

			opsForValue().set(key, value);

			if (!value.equals(opsForValue().get(key))) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

	/**
	 * 更新原值，redis更新有时会出其他错误，加一次更新验证确保数据确实被更新成功了
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 *            单位为秒
	 * @return
	 */
	public boolean updateex(String key, String value, long timeout) {

		if (value == null || "".equals(value)) {
			return false;
		}

		opsForValue().set(key, value, timeout, TimeUnit.SECONDS);

		/** 如果缓存中被更新的值与当前原值匹配，返回更新成功,否则失败 */
		if (!value.equals(opsForValue().get(key))) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 更新原值，redis更新有时会出其他错误，加一次更新验证确保数据确实被更新成功了
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean updateex(String key, String value, long timeout,
			TimeUnit unit) {

		if (value == null || "".equals(value)) {
			return false;
		}

		opsForValue().set(key, value, timeout, unit);

		/** 如果缓存中被更新的值与当前原值匹配，返回更新成功,否则失败 */
		if (!value.equals(opsForValue().get(key))) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {

		return opsForValue().get(key) == null ? opsForValue().get(key)
				: opsForValue().get(key);

	}

	/**
	 * 获取单个值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key, String defaultValue) {

		return opsForValue().get(key) == null ? defaultValue : opsForValue()
				.get(key);

	}

	/***** set常用操作 start ****/

	/**
	 * 添加到Set中
	 * 
	 * @param key
	 *            key值
	 * @param value
	 * @return
	 */
	public long addSet(String key, String... value) {

		return opsForSet().add(key, value);
	}

	/**
	 * @param key
	 * @param value
	 * @return 判断值是否包含在set中
	 */
	public boolean containsInSet(String key, String value) {

		return opsForSet().isMember(key, value);
	}

	/**
	 * 获取Set
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key) {

		return opsForSet().members(key);
	}

	/**
	 * 从set中删除value
	 * 
	 * @param key
	 * @return
	 */
	public long removeSetValue(String key, Object... value) {

		return opsForSet().remove(key, value);
	}

	/**
	 * 检查Set长度
	 * 
	 * @param key
	 * @return
	 */
	public long countSet(String key) {

		if (key == null) {
			return 0;
		}

		return opsForSet().size(key);
	}

	/****** set常用操作end ******/

	/***** list操作 ****/
	/**
	 * 压栈 主要解决评论盖楼，将最新评论数据压栈进去，展现时从最新的数据开始展现
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listStackPush(String key, String value) {
		return opsForList().leftPush(key, value);
	}

	/**
	 * 出栈 主要解决评论盖楼，将最新评论数据压栈进去，展现时从最新的数据开始展现
	 * 
	 * @param key
	 * @return
	 */
	public String listStackPop(String key) {
		return opsForList().leftPop(key);
	}

	/**
	 * 写入队列 正常list操作
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long listPushQueue(String key, String value) {

		return opsForList().rightPush(key, value);
	}

	/**
	 * 出队列
	 * 
	 * @param key
	 * @return
	 */
	public String listPopQueue(String key) {
		return opsForList().leftPop(key);
	}

	/**
	 * 获取栈/队列长度
	 * 
	 * @param key
	 * @return
	 */
	public Long listLength(String key) {
		return opsForList().size(key);
	}

	/**
	 * 范围检索取值范围数据
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> listRange(String key, long start, long end) {

		return opsForList().range(key, start, end);
	}

	/**
	 * 移除list中某个元素
	 * 
	 * @param key
	 * @param i
	 *            i=0 时 表示 将key的list中所有数据，所有为value的数据全部删除掉 i=1 时
	 *            表示将将key的list中的数据，为value的数据删除一个 i=2 时
	 *            表示将将key的list中的数据，为value的数据删除两个
	 * @param value
	 */
	public void listRemove(String key, long i, String value) {
		opsForList().remove(key, i, value);
	}

	/**
	 * 检索index对应的值
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String listIndex(String key, long index) {
		return opsForList().index(key, index);
	}

	/**
	 * 更新list中第index元素对应的value
	 * 
	 * @param key
	 * @param index
	 * @param value
	 */
	public void listUpdate(String key, long index, String value) {
		opsForList().set(key, index, value);

	}

	/**
	 * 获取某个key对应list全部数据
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> listGetAll(String key) {
		return opsForList().range(key, 0, opsForList().size(key));
	}

	/**
	 * 裁剪 注意： 此方法有bug无法删除数据，trim不可用
	 * 
	 * @param key
	 * @param start
	 * @param end
	 */
	// public void listdelete(String key, long start, long end) {
	// opsForList().trim(key, start, end);
	// }

	/****** list常用操作end ******/

	/****** hashset常用操作start ******/

	/**
	 * 设置HashSet对象 ,hashkey和 value不可以是object类型的，会报错
	 * 
	 * @param key
	 *            key
	 * @param hashKey
	 *            hashkey
	 * @param value
	 *            value
	 * @return
	 */
	public void setHSet(String key, String hashKey, String value) {

		if (value == null)
			return;

		opsForHash().put(key, hashKey, value);

	}

	/**
	 * 获得HashSet对象
	 * 
	 * @param key
	 *            key
	 * @param hashKey
	 *            hashkey
	 * @return Object
	 */
	public Object getHSet(String key, String hashKey) {

		return opsForHash().get(key, hashKey);
	}

	/**
	 * 删除HashSet对象
	 * 
	 * @param key
	 *            key
	 * @param hashKeys
	 *            hashKeys 传入的Object数组必须为String数组转为Object的类型才可以使用 Object []
	 *            haskeys = new String[]{"delHSetHashKey","delHSetHashKey2"};
	 *            baseRedisTemplate.delHSet("delHSetTest", haskeys);
	 * @return 删除的记录数
	 */
	public void delHSet(String key, Object... hashKeys) {

		opsForHash().delete(key, hashKeys);

	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 *            key
	 * @param hashKey
	 *            hashKey
	 * @return
	 */
	public Boolean existsHSet(String key, String hashKey) {

		return opsForHash().hasKey(key, hashKey);
	}

	/**
	 * 返回 Set 指定的哈希集key对应的所有hashkey值，但不是所有hashkey对应的set值
	 * 
	 * @param key
	 * @return
	 */

	public Set<Object> getHSetKeys(String key) {

		return opsForHash().keys(key);

	}

	/**
	 * 返回 key 指定的哈希key值总数
	 * 
	 * @param key
	 * @return
	 */
	public long getHSetLength(String key) {

		return opsForHash().size(key);

	}

	/****** hashset常用操作end ******/

	/****** socrtedset常用操作start ******/

	/**
	 * 设置排序集合
	 * 
	 * @param key
	 * @param score
	 * @param value
	 * @return
	 */
	public boolean addSortedSet(String key, long score, String value) {

		return opsForZSet().add(key, value, score);
	}

	/**
	 * 查找出满足一定范围分数的Set集合
	 * 
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @param orderByDesc
	 *            orderByDesc为true降序排列，为false升序排列
	 * @return
	 */
	public Set<String> getSoredSet(String key, double startScore,
			double endScore, boolean orderByDesc) {

		if (orderByDesc) {
			return opsForZSet().reverseRangeByScore(key, startScore, endScore);
		} else {
			return opsForZSet().rangeByScore(key, startScore, endScore);
		}
	}

	/**
	 * 计算排序长度
	 * 
	 * @param key
	 * @param startScore
	 * @param endScore
	 * @return
	 */
	public long countSoredSet(String key, double max, double min) {

		return opsForZSet().count(key, min, max);

	}

	/**
	 * 删除排序集合中部分元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long deleteSortedSet(String key, Object... values) {

		return opsForZSet().remove(key, values);

	}

	/**
	 * 获得排序打分
	 * 
	 * @param key
	 * @return
	 */
	public Double getSortedSetScore(String key, Object o) {

		return opsForZSet().score(key, o);

	}

	/****** socrtedset常用操作end ******/

	/**
	 * 获取满足正则表达式的set集合数据
	 * 
	 * @param parten
	 * @return
	 */
	public Set<byte[]> getKeys(String pattern) {

		RedisConnection redisConnection = null;

		Set<byte[]> set = null;

		try {

			redisConnection = jedisConnectionFactory.getConnection();

			set = redisConnection.keys(pattern.getBytes());

		} finally {
			/** 如果redisConnection不为空，关闭改连接 */
			if (redisConnection != null) {
				redisConnection.close();
			}
		}

		return set;
	}

	/**
	 * 获取满足正则表达式的set集合数据
	 * 
	 * @param parten
	 * @return
	 */
	public void deleteKeys(String pattern) {

		/** 从redis连接工厂获取jedis连接 */
		RedisConnection redisConnection = null;

		try {

			redisConnection = jedisConnectionFactory.getConnection();

			Set<byte[]> keys = redisConnection.keys(pattern.getBytes());

			/** 如果set不等于空，则遍历set集合，获取每个key，将获取的key从redis删除掉 **/
			if (keys != null) {
				for (Iterator<byte[]> iter = keys.iterator(); iter.hasNext();) {

					String key = new String(iter.next());

					delete(key);
				}
			}
		} finally {
			/** 如果redisConnection不为空，关闭改连接 */
			if (redisConnection != null) {
				redisConnection.close();
			}
		}

	}

}
