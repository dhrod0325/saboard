package kr.oks.saboard.core.constants;

public interface Constants {
	String LOGIN_MEMBER_DOMAIN = "loginMemberDomain";
	/**
	 * 파일 유무 확인용
	 */
	String HAS_FILE_NO = "NO";
	String HAS_FILE_YES = "YES";

	/**
	 * BoardController 컨트롤러 매핑용
	 */
	String PREFIX_URL = "/";

	String URL_BOARD_WRITE = PREFIX_URL + "getBoardWriteView.do";
	String URL_BOARD_MODIFY = PREFIX_URL + "getBoardModifyView.do";
	String URL_BOARD_LIST = PREFIX_URL + "getBoardListView.do";
	String URL_BOARD_DETAIL = PREFIX_URL + "getBoardDetailView.do";
	String URL_BOARD_INSERT = PREFIX_URL + "getBoardInsert.do";
	String URL_BOARD_INSERT_REPLY = PREFIX_URL + "getBoardInsertReply.do";
	String URL_BOARD_DELETE = PREFIX_URL + "getBoardDelete.do";

	String VIEW_NAME_BOARD_ERROR = "board/boardErrorView";
	String VIEW_NAME_BOARD_LIST = "board/boardListView";
	String VIEW_NAME_BOARD_WRITE = "board/boardWriteView";
	String VIEW_NAME_BOARD_DETAIL = "board/boardDetailView";

	/**
	 * AdminController 매핑용
	 */
	String URL_ADMIN_ROOT = PREFIX_URL + "admin/";
	String URL_ADMIN_INDEX = URL_ADMIN_ROOT + "getAdmin.do";

	String URL_ADMIN_BOARD_ADD = URL_ADMIN_ROOT + "getBoardAdd.do";
	String URL_ADMIN_BOARD_MODIFY = URL_ADMIN_ROOT + "getBoardModify.do";
	String URL_ADMIN_BOARD_DELETE = URL_ADMIN_ROOT + "getBoardDelete.do";

	String VIEW_NAME_ADMIN_INDEX = "board_admin/index";
	String VIEW_NAME_ADMIN_BOARD_ADD = "board_admin/board_views/boardAdd";
	String VIEW_NAME_ADMIN_BOARD_MODIFY = "board_admin/board_views/boardModify";
	String VIEW_NAME_ADMIN_BOARD_DELETE = "board_admin/board_views/boardDelete";
	
	String URL_LOGIN_JOIN = PREFIX_URL + "join.do";
	String URL_LOGIN = PREFIX_URL + "login.do";
	String URL_LOOUT = PREFIX_URL + "logout.do";
	
	String VIEW_NAME_LOGIN = "common/login/login_view";
	String VIEW_NAME_JOIN = "common/login/join_view";
	
	String UPLOAD_DIRECTORY = "upload_file_directory";

	String CR = "\r";
	String LF = "\n";
	String CRLF = CR + LF;

	String FILE_SEPARATOR = System.getProperty("file.separator");
	String LINE_SEPARATOR = System.getProperty("line.separator");

	boolean IS_MAC = CR.equals(LINE_SEPARATOR);
	boolean IS_UNIX = LF.equals(LINE_SEPARATOR);
	boolean IS_WINDOWS = CRLF.equals(LINE_SEPARATOR);

	String CHARSET_UTF_8 = "UTF-8";
	String CHARSET_ISO_8859_1 = "ISO8859-1";
	String CHARSET_EUC_KR = "EUC-KR";

	String HTTP_METHOD_GET = "GET";
	String HTTP_METHOD_POST = "POST";
	String HTTP_METHOD_HEAD = "HEAD";
	String HTTP_METHOD_PUT = "PUT";
	String HTTP_METHOD_DELETE = "DELETE";
	String HTTP_METHOD_OPTIONS = "OPTIONS";
	String HTTP_METHOD_TRACE = "TRACE";

	String KEY_USER_ID = "username";
	String KEY_USER_PASSWORD = "password";

	
}
