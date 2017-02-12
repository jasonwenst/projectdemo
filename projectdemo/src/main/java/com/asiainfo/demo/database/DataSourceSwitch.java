package com.asiainfo.demo.database;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;


/**
 * 数据源切换类
 */
public class DataSourceSwitch {
	
	private static transient Log log = LogFactory.getLog(DataSourceSwitch.class);

	private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	
	public static final String MYSQL_DATA_SOURCE = "ds_mysql";
	public static final String ORACLE_DATA_SOURCE = "ds_oracle";
	
	/**
	 * 保存数据源的KEY
	 */
	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}
	
	/**
	 * 取得数据源的KEY，默认使用PAY
	 */
	public static String getDataSourceType() {
		String dataSource = (String) contextHolder.get();
		if (StringUtils.isEmpty(dataSource)) {
			log.warn("dataSource is not set, please use DataSourceSwitch.setDataSourceType()");
			dataSource = DataSourceSwitch.MYSQL_DATA_SOURCE;
		}
		return dataSource;
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}