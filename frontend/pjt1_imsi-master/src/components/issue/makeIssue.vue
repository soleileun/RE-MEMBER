<template>
  <div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <h3 class="modal-title">이슈 추가하기</h3>
          <button type="button" class="close" data-dismiss="modal">x</button>
        </div>
        <div class="modal-body">
          <div class="col-md-12">
            <div class="card">
              <div class="header">
                <h4 class="title">이슈 정보 등록</h4>
              </div>
              <div class="content">
                <form>
                  <div class="row">
                    <div class="col-md-7">
                      <div class="form-group">
                        <label>프로젝트 명</label>
                        <input
                          type="text"
                          class="form-control"
                          disabled
                          placeholder="Company"
                          value="Creative Code Inc."
                        />
                      </div>
                    </div>
                    <div class="col-md-5">
                      <div class="form-group">
                        <label>등록자</label>
                        <input
                          type="text"
                          class="form-control"
                          disabled
                          placeholder="Username"
                          value="michael23"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>이슈 제목</label>
                        <input type="text" class="form-control" placeholder="이슈 제목을 적으세요" value />
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>선제 작업</label>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="등록하려는 이슈 전 선제되야 하는 작업을 고르시오"
                          value
                        />
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>이슈 상태</label>
                        <input type="text" class="form-control" placeholder="생성/진행/완료" />
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>이슈 종류</label>
                        <input type="text" class="form-control" placeholder="에픽/스토리/태스크" />
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>이슈 중요도</label>
                        <input type="text" class="form-control" placeholder="상/중/하" />
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>이슈 내용</label>
                        <textarea
                          rows="5"
                          class="form-control"
                          placeholder="이슈 내용을 적으세요"
                          value="Mike"
                        ></textarea>
                      </div>
                    </div>
                  </div>

                  <button type="submit" class="btn btn-info btn-fill pull-right">
                    Update
                    Issue
                  </button>
                  <div class="clearfix"></div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</template>
