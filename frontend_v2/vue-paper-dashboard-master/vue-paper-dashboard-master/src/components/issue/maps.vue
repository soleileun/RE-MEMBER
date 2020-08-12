<template>
  <div id="dmap">
    <div>
      <button class="btn btn-info btn-simple btn-xs"
       @click="findCafe()">카페 검색하기</button>
      
      <br />
    </div>

    <!-- <div id="map" style="width: 700px; height: 500px;"></div> -->
    <div class="map_wrap">
      <div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
      <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
      </div>
    </div>
  </div>
</template>

<script>
import Constant from "../../Constant";

export default {
  name: "dmap",
  data() {
    return {
      geocoder: {},
      markerCenter: {},
      map: {},
      mapContainer: {},
      markerImage: {},
      markers: [],
      tmpData: [],
      mapOption: {},
      imageSrc: {},
      imageOption: {},
      imageSize: {},
      infowindow: {},
      category: {},
    };
  },
  methods: {
    address() {
      new daum.Postcode({
        oncomplete: function (data) {
          alert(data.address);
          // data.address로 회원가입시 주소를 입력하면 됨.
        },
      }).open();
    },

    // 키워드 검색 완료 시 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {        
        for (var i = 0; i < data.length; i++) {
          this.displayMarker(data[i]);
        }
      } else {
        //alert("주위 검색 실패!");
        console.log("실패");
      }
    },

    displayMarker(place) {
      // 마커를 생성하고 지도에 표시합니다

      var imageSrc2 =
          "https://cdn3.iconfinder.com/data/icons/pin-4/100/pin_10-512.png", // 마커이미지의 주소입니다
        imageSize2 = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption2 = {
          offset: new kakao.maps.Point(27, 69),
        }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage2 = new kakao.maps.MarkerImage(
        imageSrc2,
        imageSize2,
        imageOption2
      );

      var marker2 = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(place.y, place.x),
        image: markerImage2,
      });

      // 마커에 클릭이벤트를 등록합니다
      kakao.maps.event.addListener(marker2, "click", () => {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다

        var content =
          '<div style="width:300px;"><div class="placeinfo" style="position: relative; width: 100%; border-radius: 6px; border: 1px solid #ccc; border-bottom: 2px solid #ddd; padding-bottom: 10px; background: #fff;">' +
          '   <a class="title" style="font-weight: bold; width:100%;font-size: 14px;  border-radius: 6px 6px 0 0;  margin: -1px -1px 0 -1px;  padding: 10px;   color: #fff;   background: #d95050;   background: #d95050 no-repeat right 14px center;" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a><br><br>";

        if (place.road_address_name) {
          content +=
            '    <span style="margin: 5px 5px 0 5px;  cursor: default;  font-size: 13px;"title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" style = ""color: #999;  font-size: 11px;  margin-top: 0; title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content +=
            '    <span style="margin: 5px 5px 0 5px;  cursor: default;  font-size: 13px;"title="' +
            place.address_name +
            '">' +
            place.address_name +
            "</span>";
        }

        content +=
          '    <span class="tel" style="color: #0f7833; margin: 5px 5px 0 5px;  cursor: default;  font-size: 13px;">' +
          place.phone +
          "</span>" +
          "</div>" +
          '<div class="after" style="content: "";  position: relative; margin-left: -12px;  left: 50%;  width: 22px;  height: 12px;  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");"></div></div>';
        /* category
																	.setContent('<div style="padding:5px;font-size:12px;">'
																			+ place.place_name
																			+ '</div>'); */
        this.category.setContent(content);
        this.category.open(this.map, marker2);
      });//
    },
    addMarker(position) {
      // 마커를 생성합니다

      var marker = new kakao.maps.Marker({
        position: position,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);

      // 생성된 마커를 배열에 추가합니다
      this.markers.push(marker);
    },

    // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
    },

    searchAddrFromCoords(coords, callback) {
      // 좌표로 행정동 주소 정보를 요청합니다
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback
      );
    },

    searchDetailAddrFromCoords(coords, callback) {
      // 좌표로 법정동 상세 주소 정보를 요청합니다
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    },

    // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
    displayCenterInfo(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById("centerAddr");

        for (var i = 0; i < result.length; i++) {
          // 행정동의 region_type 값은 'H' 이므로
          if (result[i].region_type === "H") {
            infoDiv.innerHTML = result[i].address_name;
            break;
          }
        }
      }
    },

    mountedTesting() {
      let len = this.users.length;

      for (var i = 0; i < len; i++) {
        //console.log(this.users[i].address2);
        let address = this.users[i].address1;
        console.log(address);
        this.geocoder.addressSearch(address, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            console.log("sucess!");
            //this.markerCenter.setMap(null);
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              position: coords,
            });
            this.markers.push(marker);
          }
        });
      }

      setTimeout(() => {
        var totalX = 0;
        var totalY = 0;
        var leng = this.markers.length;
        console.log("leng is " + leng);

        for (var i = 0; i < leng; i++) {
          totalX += this.markers[i].getPosition().Ha;
          totalY += this.markers[i].getPosition().Ga;
        }
        totalX /= leng;
        totalY /= leng;
        console.log(totalX, totalY);

        var coords2 = new kakao.maps.LatLng(totalX, totalY);
        this.markers = [];
        this.markerCenter = new kakao.maps.Marker({
          map: this.map,
          position: coords2,
          image: this.markerImage,
        });

        //this.markerCenter.setMap(this.map);
        this.map.setCenter(coords2);

        var iwContent = '<div style="padding:5px;">Center</div>';

        // 인포윈도우를 생성합니다
        this.infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(this.markerCenter, "mouseover", () => {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          this.infowindow.open(this.map, this.markerCenter);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(this.markerCenter, "mouseout", () => {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
          this.infowindow.close();
        });

        //markerCenter 에 대한 주소 상세
        this.searchDetailAddrFromCoords(coords2, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address
              ? "<div>도로명주소 : " +
                result[0].road_address.address_name +
                "</div>"
              : "";
            detailAddr +=
              "<div>지번 주소 : " + result[0].address.address_name + "</div>";

            var content =
              '<div class="bAddr" style="padding: 5px;  text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;">' +
              '<span class="title">법정동 주소정보</span>' +
              detailAddr +
              "</div>";

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            this.infowindow.setContent(content);
          }
        });
      }, 300);
      // 주소 검색 후 markers 배열에 담기
    },

    findCafe() {
      setTimeout(() => {
        this.category = new kakao.maps.InfoWindow({
          zIndex: 1,
          removable: true,
        });

        // 주변 카페 검색
        // 장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places(this.map);

        ps.categorySearch("CE7", this.placesSearchCB, {
          useMapBounds: true,
        });
      }, 100);
    },

    testing() {
      var input = document.getElementById("address").value;

      this.geocoder.addressSearch(input, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          //   console.log("markerCenter is " + this.markerCenter);
          this.markerCenter.setMap(null);
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          this.addMarker(coords);

          var totalX = 0;
          var totalY = 0;
          var len = this.markers.length;
          for (var i = 0; i < len; i++) {
            totalX += this.markers[i].getPosition().Ha;
            totalY += this.markers[i].getPosition().Ga;
          }
          totalX /= len;
          totalY /= len;

          var coords = new kakao.maps.LatLng(totalX, totalY);

          this.markerCenter = new kakao.maps.Marker({
            map: this.map,
            position: coords,
            image: this.markerImage,
          });
          this.markerCenter.setMap(this.map);
          this.map.setCenter(coords);

          // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
          this.category = new kakao.maps.InfoWindow({
            zIndex: 1,
            removable: true,
          });

          // 주변 카페 검색
          // 장소 검색 객체를 생성합니다
          var ps = new kakao.maps.services.Places(this.map);

          ps.categorySearch("CE7", this.placesSearchCB, {
            useMapBounds: true,
          });

          var iwContent = '<div style="padding:5px;">Center</div>';

          // 인포윈도우를 생성합니다
          this.infowindow = new kakao.maps.InfoWindow({
            content: iwContent,
          });

          // 마커에 마우스오버 이벤트를 등록합니다
          kakao.maps.event.addListener(this.markerCenter, "mouseover", () => {
            // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
            this.infowindow.open(this.map, this.markerCenter);
          });

          // 마커에 마우스아웃 이벤트를 등록합니다
          kakao.maps.event.addListener(this.markerCenter, "mouseout", () => {
            // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
            this.infowindow.close();
          });

          //markerCenter 에 대한 주소 상세
          this.searchDetailAddrFromCoords(coords, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              var detailAddr = !!result[0].road_address
                ? "<div>도로명주소 : " +
                  result[0].road_address.address_name +
                  "</div>"
                : "";
              detailAddr +=
                "<div>지번 주소 : " + result[0].address.address_name + "</div>";

              var content =
                '<div class="bAddr" style="padding: 5px;  text-overflow: ellipsis;  overflow: hidden;  white-space: nowrap;">' +
                '<span class="title">법정동 주소정보</span>' +
                detailAddr +
                "</div>";

              // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
              this.infowindow.setContent(content);
              /* infowindow
															.open(map, marker); */
            }
          });
        } else {
          alert("검색 실패!");
        }
      });
    },
  },

  created() {
    let pid = this.$route.params.pid;
    this.$store.dispatch(Constant.GET_ADDRESS_LIST, {
      pid: pid,
    });
    this.markerCenter = new kakao.maps.LatLng(33.450701, 126.570667);
  },

  computed: {
    users() {
      return this.$store.state.mapstore.users;
    },
  },
  mounted() {
    this.geocoder = new kakao.maps.services.Geocoder();

    (this.mapContainer = document.getElementById("map")), // 지도를 표시할 div
      (this.mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 4,
        // 지도의 확대 레벨
      });

    // 지도를 생성합니다
    this.map = new kakao.maps.Map(this.mapContainer, this.mapOption);

    (this.imageSrc =
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png"), // 마커이미지의 주소입니다
      (this.imageSize = new kakao.maps.Size(64, 69)), // 마커이미지의 크기입니다
      (this.imageOption = {
        offset: new kakao.maps.Point(27, 69),
      }); // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    this.markerImage = new kakao.maps.MarkerImage(
      this.imageSrc,
      this.imageSize,
      this.imageOption
    );

    // // 마커를 생성합니다
    // this.markerCenter = new kakao.maps.Marker({
    //   position: new kakao.maps.LatLng(33.450701, 126.570667),
    //   image: this.markerImage,
    //   // 마커이미지 설정
    // });

    // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
    kakao.maps.event.addListener(this.map, "idle", () => {
      this.searchAddrFromCoords(this.map.getCenter(), this.displayCenterInfo);
    });

     setTimeout(() => {
        this.mountedTesting();
      }, 100);
    // this.mountedTesting();
    //
    setTimeout(() => {
      this.findCafe()
    }, 500);
  },
};
</script>

<style scoped lang="scss">
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}

.title {
  font-weight: bold;
  display: block;
}

.hAddr {
  position: absolute;
  left: 10px;
  top: 10px;
  border-radius: 2px;
  background: #fff;
  background: rgba(255, 255, 255, 0.8);
  z-index: 1;
  padding: 5px;
}

#centerAddr {
  display: block;
  margin-top: 2px;
  font-weight: normal;
}

.bAddr {
  padding: 5px;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}

.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}

.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}

.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}

.placeinfo a.title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 no-repeat right 14px center;
}

.placeinfo .tel {
  color: #0f7833;
}

.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>