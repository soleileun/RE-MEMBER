<template>
  <div>   
	
	
    <div class="wrapper">
      <div class="sidebar" data-color="purple" data-image="./assets/issue_img/sidebar-5.jpg">
        <div class="sidebar-wrapper">
          <div class="logo">
            <a href class="simple-text">Project Info</a>
          </div>

          <ul class="nav">
            <li class="active">
              <a href="/main">
                <i class="pe-7s-graph"></i>
                <p>Issue Board</p>
              </a>
            </li>

            <li>
              <a href="teamInfo.html">
                <i class="pe-7s-note2"></i>
                <p>Team Info</p>
              </a>
            </li>
            <li>
              <router-link :to="'/maps'">지도</router-link>

              <!-- <a href="maps.html"> <i class="pe-7s-map-marker"></i> -->
              <p>Maps</p>
              <!-- </a> -->
            </li>
          </ul>
        </div>
      </div>

      <div class="content">
        <div class="container-fluid">
          <div class="row">
            <div class="col-5">
              <div class="card">
                <div class="header">
                  <h4 class="card-title">이슈 처리 현황 그래프</h4>
                </div>
                <div class="content">
                  <div class="ct-chart">
                    <canvas id="myChart" style="height: 50%; width: 50%;"></canvas>
                  </div>
                  <div class="footer">
                    <hr />
                    <div class="stats">
                      <i class="fa fa-history"></i> Updated 3 minutes ago
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-7">
              <div class="card">
                <div class="header">
                  <h4 class="title">최근 프로젝트 이슈</h4>
                  <p class="category">날짜순</p>
                </div>
                <div class="content">
                  <div class="overflow-auto table-responsive">
                    <table class="table">
                      <thead>
                        <th>날짜</th>
                        <th>제목</th>
                        <th class="td-actions text-right">상태</th>
                      </thead>
                      <tbody>
                        <tr>
                          <td>2020-08-03 21:09</td>
                          <td>이슈 테스팅1</td>
                          <td class="td-actions text-right">
                            <div style="color: red">진행중</div>
                          </td>
                        </tr>
                        <tr>
                          <td>2020-08-01 20:02</td>
                          <td>이슈 테스팅2</td>
                          <td class="td-actions text-right">
                            <div style="color: yellowgreen">완료</div>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="footer">
                    <hr />
                    <div class="stats">
                      <i class="fa fa-history"></i> Updated 3 minutes ago
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-4">
              <div class="card">
                <div class="header">
                  <h4 class="title" style="color: skyblue">생성된 이슈</h4>
                  <p class="category">날짜순</p>
                  <p align="right">
                    <button @click="openModal"
                      type="button"
                      rel="tooltip"
                      title="Add"
                      class="btn btn-info btn-simple btn-xs"
                      data-toggle="modal"
                      data-target="#myModal"
                    >
                      <i class="fa fa-edit"></i>
                    </button>
                  </p>
                </div>
                <div class="content">
                  <div class="overflow-auto table-responsive">
                    <table class="table">
                      <tbody>
                        <tr>
                          <td>2020-08-01 10:01</td>
                          <td>생성된 이슈 테스팅1</td>
                          <td class="td-actions text-right">
                            <button
                              type="button"
                              rel="tooltip"
                              title="Remove"
                              class="btn btn-danger btn-simple btn-xs"
                            >
                              <i class="fa fa-times"></i>
                            </button>
                          </td>
                        </tr>
                        <tr>
                          <td>2020-08-01 20:09</td>
                          <td>생성된 이슈 테스팅2</td>
                          <td class="td-actions text-right">
                            <button
                              type="button"
                              rel="tooltip"
                              title="Remove"
                              class="btn btn-danger btn-simple btn-xs"
                            >
                              <i class="fa fa-times"></i>
                            </button>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="footer">
                    <hr />
                    <div class="stats">
                      <i class="fa fa-history"></i> Updated 3 minutes ago
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-4">
              <div class="card">
                <div class="header">
                  <h4 class="title" style="color: red">진행중인 이슈</h4>
                  <p class="category">날짜순</p>
                  <p align="right">
                    <button
                      type="button"
                      rel="tooltip"
                      title="Add"
                      data-toggle="modal"
                      data-target="#myModal"
                      class="btn btn-info btn-simple btn-xs"
                    >
                      <i class="fa fa-edit"></i>
                    </button>
                  </p>
                </div>
                <div class="content">
                  <div class="overflow-auto table-responsive">
                    <table class="table" id="ongoingTable">
                      <tbody class="t_sortable">
                        <tr>
                          <td style="width: 40%; font-size: 80%">2020-08-03</td>
                          <td
                            style="width: 100%; font-size: 100%; overflow: hidden; text-overflow: ellipsis;"
                          >
                            진행중
                            이슈1
                          </td>
                          <td class="td-actions text-right">
                            <button
                              type="button"
                              rel="tooltip"
                              title="Remove"
                              class="btn btn-danger btn-simple btn-xs"
                            >
                              <i class="fa fa-times"></i>
                            </button>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="footer">
                    <hr />
                    <div class="stats">
                      <i class="fa fa-history"></i> Updated 3 minutes ago
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-md-4">
              <div class="card">
                <div class="header">
                  <h4 class="title" style="color: yellowgreen">완료된 이슈</h4>
                  <p class="category">날짜순</p>
                  <p align="right">
                    <button
                      type="button"
                      rel="tooltip"
                      title="Add"
                      data-toggle="modal"
                      data-target="#myModal"
                      class="btn btn-info btn-simple btn-xs"
                    >
                      <i class="fa fa-edit"></i>
                    </button>
                  </p>
                </div>
                <div class="content">
                  <div class="table-full-width">
                    <table class="table" id="doneTable">
                      <tbody class="t_sortable">
                        <tr>
                          <td>2020-08-03 23:02</td>
                          <td>완료 이슈 테스팅1</td>
                          <td class="td-actions text-right">
                            <button
                              type="button"
                              rel="tooltip"
                              title="Remove"
                              class="btn btn-danger btn-simple btn-xs"
                            >
                              <i class="fa fa-times"></i>
                            </button>
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div class="footer">
                    <hr />
                    <div class="stats">
                      <i class="fa fa-history"></i> Updated 3 minutes ago
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "issue1",
  data() {
    return {
      data: [],
    };
  },

  mounted() {
    var colors = [
      "skyblue",
      "red",
      "yellowgreen",
      "#c3e6cb",
      "#dc3545",
      "#6c757d",
    ];

    var donutOptions = {
      cutoutPercentage: 30,
      legend: {
        position: "bottom",
        padding: 5,
        labels: {
          pointStyle: "circle",
          usePointStyle: true,
        },
      },
    };
    var chDonutData1 = {
      labels: ["생성된 이슈", "진행중인 이슈", "완료된 이슈"],
      datasets: [
        {
          backgroundColor: colors.slice(0, 3),
          borderWidth: 0,
          data: [30, 71, 11],
        },
      ],
    };
    var chDonut1 = document.getElementById("myChart");
    if (chDonut1) {
      new Chart(chDonut1, {
        type: "pie",
        data: chDonutData1,
        options: donutOptions,
      });
    }
  },

  methods: {
	  openModal() {
      // Get the modal
      var modal = document.getElementById("myModal");

      // Get the <span> element that closes the modal
      var span = document.getElementsByClassName("close")[0];

      // When the user clicks on the button, open the modal
      modal.style.display = "block";

      // When the user clicks on <span> (x), close the modal
      span.onclick = function () {
        modal.style.display = "none";
      };

      // When the user clicks anywhere outside of the modal, close it
      window.onclick = function (event) {
        if (event.target == modal) {
          modal.style.display = "none";
        }
      };
    },
  },
};
</script>

<style>
</style>