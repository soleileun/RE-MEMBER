export default {
    //게시판 CRUD
    GET_BOARDLIST: 'getBoardList', //bstate에 맞는 보드 리스트
    GET_BOARD: 'getBoard',
    ADD_BOARD: 'addBoard',
    MODIFY_BOARD: 'modifyBoard',
    REMOVE_BOARD: 'removeBoard',
    SEARCH_BOARD_TITLE: 'searchBoardByTitle',
    SEARCH_BOARD_WRITER: 'searchBoardByWriter',
    READ_BOARD: 'readBoard',
    // COMPLETE_BOARD : 'completeTodo',
    // CLEAR_BOARD : 'clearTodo'

    //댓글 CRUD
    GET_COMMENTLIST: 'getCommentList',
    ADD_COMMENT: 'addComment',
    REMOVE_COMMENT: 'removeComment',
    CLEAR_COMMENT: 'clearComment',
    PICK_COMMENTS: 'pickComment',

    //프로젝트 CRUD
    GET_PROJECTLIST_BY_PMEMBER: 'getProjectListByPmember',
    GET_PROJECT: 'getProject',
    // GET_CURRENT_MEMBER_COUNT: 'getCurrentMemberCount',
    GET_PROJECT_MEMBER_BY_PID: 'getProjectMemberByPid',
    ADD_PROJECT: 'addProject',
    //인재풀
    GET_POOLLIST: 'getPoolList',
    SEARCH_POOLIST: 'searchPoolist',

    //리쿠르트
    GET_RECRUITLIST: 'getRecruitList',
    GET_RECRUIT: 'getRecruit',
    ADD_RECRUIT: 'addRecruit',
    // ADD_PINTEREST: 'addPinterest',
    SEARCH_RECRUIT_BY_TAG_ADDR: 'searchRecruitByTagAddr',
    SEARCH_RECRUIT_BY_TAG: 'searchRecruitByTag',
    SEARCH_RECRUIT_BY_ADDR: 'searchRecruitByAddr',
    REMOVE_RECRUIT: 'removeRecruit',
    SEARCH_RECRUIT: 'searchRecruit',


    //인재풀, 모집 공용스택
    GET_FILTERED_POOLLIST: 'getFilteredPoolList',
    GET_SIDOLIST: 'getSidoList',
    GET_GUGUNLIST: 'getGugunList',
    GET_DONGLIST: 'getDongList',
    SEARCH_POOL_BY_TAG_ADDR: 'searchPoolByTagAddr',
    SEARCH_POOL_BY_TAG: 'searchPoolByTag',
    SEARCH_POOL_BY_ADDR: 'searchPoolByAddr',
    GET_EXTENDPOOLLIST: 'extendPoolList',



    //이거 그 뭐야 채팅 관련
    GET_CHATROOMLIST: 'getChatroomList',
    GET_CHATROOMMEMBER: 'getChatroomMember',
    ADD_CHATROOM: 'addChatroom',
    GET_CHATLIST: 'getChatList',
    SEND_CHAT: 'sendChat',
    REMOVE_CHATROOM: 'removeChatroom',
    REMOVE_CHAT: 'removeChat',
    CHAT_READ: 'chatRead',
    GET_CHATROOMONETOONE: 'getChatroomOneToOne',


    // 이슈 관련
    GET_ISSUELIST: 'getIssueList',
    GET_ISSUELIST_BY_STATE: 'getIssueListByState',
    GET_ISSUE: 'getIssue',
    ADD_ISSUE: 'addIssue',
    MODIFY_ISSUE: 'modifyIssue',
    REMOVE_ISSUE: 'removeIssue',
    CHANGE_RESPONSE: 'changeResponse',
    REMOVE_ISSUE: 'removeIssue',
    UPDATE_ISSUE_BY_STATE: 'updateIssueByState',

    //맵 관련
    GET_ADDRESS_LIST: 'getAddressList'
}