package kr.oks.saboard.core.constants;

public interface ConstantsDB {
	String MYSQL_SURFFIX = "ServiceMysql";
	String ORACLE_SURFFIX = "ServiceOracle";

	String MYSQL_BOARD_SERVICE_BEAN_NAME = "board" + MYSQL_SURFFIX;
	String ORACLE_BOARD_SERVICE_BEAN_NAME = "board" + ORACLE_SURFFIX;

	String MYSQL_COMMON_SERVICE_BEAN_NAME = "common" + MYSQL_SURFFIX;
	String ORACLE_COMMON_SERVICE_BEAN_NAME = "common" + ORACLE_SURFFIX;

	
	
	
	
	String COMMON_SERVICE_BEAN_NAME = MYSQL_COMMON_SERVICE_BEAN_NAME;
	
	String BOARD_SERVICE_BEAN_NAME = MYSQL_BOARD_SERVICE_BEAN_NAME;
}
