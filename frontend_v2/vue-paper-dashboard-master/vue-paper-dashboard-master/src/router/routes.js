import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// 우리꺼 루트
import main from "./link/main.vue";
import notice from "./link/notice.vue";
import freeboard from "./link/freeboard.vue";
import qaboard from "./link/qaboard.vue";
import recruit from "./link/recruit.vue";
import service from "./link/service.vue";
import profile from "./link/profile.vue";
import othersprofile from "./link/othersprofile.vue";
// 우리 리브
import findIDPW from "../components/user/findIDPW.vue";
import leave from "../components/user/leave.vue";
import editinfo from "../components/user/editinfo.vue";
import editinterest from "../components/user/editinterest.vue";
import signup from "./link/signup.vue";
import emailcheck from "./link/emailcheck.vue";
import recruit1 from "../components/recruit/recruit1.vue";
import recruitdetail from "../components/recruit/recruitdetail.vue";
import writeqa from "../components/qaboard/writeqa.vue";
import detailqa from "../components/qaboard/detailqa.vue";
import modifyqa from "../components/qaboard/modifyqa.vue";
import myproject from "../components/project/myproject.vue";
import projectdetail from "../components/project/projectdetail.vue";
import makeproject from "../components/project/makeproject.vue";
import pool from "./link/pool.vue";
import poollist from "../components/pool/poollist.vue";
import writenotice from "../components/notice/writenotice.vue";
import detailnotice from "../components/notice/detailnotice.vue";
import modifynotice from "../components/notice/modifynotice.vue";
import writefree from "../components/freeboard/writefree.vue";
import detailfree from "../components/freeboard/detailfree.vue";
import modifyfree from "../components/freeboard/modifyfree.vue";
import otherproject from "../components/project/otherproject.vue";



// Admin pages
import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import Notifications from "@/pages/Notifications.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
import Typography from "@/pages/Typography.vue";
import TableList from "@/pages/TableList.vue";


const routes = [{
    path: "/",
    component: DashboardLayout,
    redirect: "/main",
    children: [

      {
        path: "main",
        name: "메인",
        component: main
      },
      {
        path: "freeboard",
        name: "자유게시판",
        component: freeboard,
      },
      {
        path: "notice",
        name: "공지사항",
        component: notice,
      },
      {
        path: "qaboard",
        name: "질문게시판",
        component: qaboard,
      },
      {
        path: "/recruit",
        name: "모집",
        component: recruit,
      },
      {
        path: "/service",
        name: "고객센터",
        component: service,
      },
      {
        path: "/pool",
        name: "인재풀",
        component: pool,
      },
      {
        path: "/mypage",
        name: "마이페이지",
        component: profile,
      },
      {
        path: "/profile/:userid",
        name: "프로필보기",
        component: othersprofile,
      },
      /////////// 잎파리

      {
        path: "/freeboard/writefree",
        name: "자유게시판 글쓰기",
        component: writefree,
      },
      {
        path: "/freeboard/detailfree/:bno",
        name: "자유게시판 글",
        component: detailfree,
      },
      {
        path: "/freeboard/modifyfree/:bno",
        name: "글 수정",
        component: modifyfree,
      },
      {
        path: "/notice/writenotice",
        name: "공지 작성",
        component: writenotice,
      },
      {
        path: "/notice/detailnotice/:bno",
        name: "공지사항 글",
        component: detailnotice,
      },
      {
        path: "/notice/modifynotice/:bno",
        name: "공지사항 수정",
        component: modifynotice,
      },

      {
        path: "/pool/poollist",
        name: "인재 리스트",
        component: poollist,
      }, {
        path: "/project/myproject/:userId",
        name: "내 프로젝트",
        component: myproject,
      },
      {
        path: "/project/:userid",
        name: "프로젝트 보기",
        component: otherproject,
      },
      {
        path: "/project/projectdetail/:pid",
        name: "프로젝트 상세",
        component: projectdetail,
      },
      {
        path: "/project/makeproject",
        name: "프로젝트 작성",
        component: makeproject,
      }, {
        path: "/qaboard/writeqa",
        name: "질문 작성",
        component: writeqa,
      },
      {
        path: "/qaboard/detailqa/:bno",
        name: "질문",
        component: detailqa,
      },
      {
        path: "/qaboard/modifyqa/:bno",
        name: "질문 수정",
        component: modifyqa,
      },
      {
        path: "/recruit/recruit1",
        name: "모집 리스트",
        component: recruit1,
      },
      {
        path: "/recruit/recruitdetail/:rnum&:pid",
        name: "모집 상세",
        component: recruitdetail,
      },

      {
        path: "/signup",
        name: "회원 가입",
        component: signup,
      },
      {
        path: "/emailcheck",
        name: "이메일 확인",
        component: emailcheck,
      }, {
        path: "/user/findid",
        name: "아이디 /비밀번호 찾기",
        component: findIDPW,
      },
      {
        path: "/user/editinfo",
        name: "개인정보 수정",
        component: editinfo,
      },
      {
        path: "/user/editinterest",
        name: "내 관심사 수정",
        component: editinterest,
      },
      {
        path: "/user/leave",
        name: "회원 탈퇴",
        component: leave,
      },
      ///////////////////////////////////////////// 여기 아래로 탬플릿
      {
        path: "dashboard",
        name: "dashboard",
        component: Dashboard
      },
      {
        path: "stats",
        name: "stats",
        component: UserProfile
      },
      {
        path: "notifications",
        name: "notifications",
        component: Notifications
      },
      {
        path: "icons",
        name: "icons",
        component: Icons
      },
      {
        path: "maps",
        name: "maps",
        component: Maps
      },
      {
        path: "typography",
        name: "typography",
        component: Typography
      },
      {
        path: "table-list",
        name: "table-list",
        component: TableList
      }
    ]
  },
  {
    path: "*",
    component: NotFound
  }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
