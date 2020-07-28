export default{
    //게시판 CRUD
    GET_BOARDLIST : 'getBoardList', //bstate에 맞는 보드 리스트
    GET_BOARD : 'getBoard',
    ADD_BOARD : 'addBoard',
    MODIFY_BOARD : 'modifyBoard',
    REMOVE_BOARD : 'removeBoard',
    SEARCH_BOARD_TITLE : 'searchBoardByTitle',
    SEARCH_BOARD_WRITER : 'searchBoardByWriter',
    // COMPLETE_BOARD : 'completeTodo',
    // CLEAR_BOARD : 'clearTodo'

    //댓글 CRUD
    GET_COMMENTLIST : 'getCommentList',
    ADD_COMMENT : 'addComment',
    REMOVE_COMMENT : 'removeComment',
    CLEAR_COMMENT : 'clearComment',

    //프로젝트 CRUD
    GET_PROJECTLIST_BY_PMEMBER : 'getProjectListByPmember',
    GET_PROJECT : 'getProject',
    GET_CURRENT_MEMBER_COUNT : 'getCurrentMemberCount', 

    //인재풀
    GET_POOLLIST : 'getPoolList',

    //리쿠르트
    GET_RECRUITLIST : 'getRecruitList',
    GET_RECRUIT : 'getRecruit',
    ADD_RECRUIT : 'addRecruit',

}