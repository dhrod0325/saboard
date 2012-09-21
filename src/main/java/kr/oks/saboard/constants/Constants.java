package kr.oks.saboard.constants;

public interface Constants {
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
	
	String VIEW_NAME_BOARD_LIST = "board/boardListView";
	String VIEW_NAME_BOARD_WRITE = "board/boardWriteView";
	String VIEW_NAME_BOARD_DETAIL = "board/boardDetailView";
	
	/**
	 * AdminController 매핑용
	 */
	String URL_ADMIN_ROOT = PREFIX_URL + "admin/";
	String URL_ADMIN_INDEX = URL_ADMIN_ROOT+"getAdmin.do";
	
	String URL_ADMIN_BOARD_ADD = URL_ADMIN_ROOT+"getBoardAdd.do";
	String URL_ADMIN_BOARD_MODIFY = URL_ADMIN_ROOT+"getBoardModify.do";
	String URL_ADMIN_BOARD_DELETE = URL_ADMIN_ROOT+"getBoardDelete.do";
	
	String VIEW_NAME_ADMIN_INDEX = "board_admin/index";
	String VIEW_NAME_ADMIN_BOARD_ADD = "board_admin/board_views/boardAdd";
	String VIEW_NAME_ADMIN_BOARD_MODIFY = "board_admin/board_views/boardModify";
	String VIEW_NAME_ADMIN_BOARD_DELETE = "board_admin/board_views/boardDelete";
	
	String UPLOAD_DIRECTORY = "upload_file_directory";
	
	
}
