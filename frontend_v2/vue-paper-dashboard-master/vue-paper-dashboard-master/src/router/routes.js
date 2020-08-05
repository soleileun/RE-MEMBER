import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";
// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// 우리꺼 루트
import main from "./link/main.vue";
import notice from "./link/notice.vue";
import user from "@/components/user/profile.vue";
import freeboard from "./link/freeboard.vue";
import qaboard from "./link/qaboard.vue";
import recruit from "./link/recruit.vue";
import service from "./link/service.vue";
// 우리 리브
import findIDPW from "../components/user/findIDPW.vue";
import leave from "../components/user/leave.vue";
import editinfo from "../components/user/editinfo.vue";
import signup from "./link/signup.vue";
import emailcheck from "./link/emailcheck.vue";
import recruit1 from "../components/recruit/recruit1.vue";
import recruitdetail from "../components/recruit/recruitdetail.vue";
import registteam from "../components/recruit/registteam.vue";
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




// Admin pages
import Dashboard from "@/pages/Dashboard.vue";
import UserProfile from "@/pages/UserProfile.vue";
import Notifications from "@/pages/Notifications.vue";
import Icons from "@/pages/Icons.vue";
import Maps from "@/pages/Maps.vue";
import Typography from "@/pages/Typography.vue";
import TableList from "@/pages/TableList.vue";


const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/main",
    children: [

      {
        path: "main",
        name: "main",
        component: main
      },
      {
        path: "user",
        name: "user",
        component: user
      },
      {
        path: "freeboard",
        name: "freeboard",
        component: freeboard,
      },
      {
        path: "notice",
        name: "notice",
        component: notice,
      },
      {
        path: "qaboard",
        name: "qaboard",
        component: qaboard,
      },
      {
        path: "/recruit",
        name: "recruit",
        component: recruit,
      },
      {
        path: "/service",
        name: "service",
        component: service,
      },
      {
        path: "/pool",
        name: "pool",
        component: pool,
      },
      /////////// 잎파리

      {
        path: "/freeboard/writefree",
        name: "writefree",
        component: writefree,
      },
      {
        path: "/freeboard/detailfree/:bno",
        name: "detailfree",
        component: detailfree,
      },
      {
        path: "/freeboard/modifyfree/:bno",
        name: "modifyfree",
        component: modifyfree,
      },
      {
        path: "/notice/writenotice",
        name: "writenotice",
        component: writenotice,
      },
      {
        path: "/notice/detailnotice/:bno",
        name: "detailnotice",
        component: detailnotice,
      },
      {
        path: "/notice/modifynotice/:bno",
        name: "modifynotice",
        component: modifynotice,
      },

      {
        path: "/pool/poollist",
        name: "poollist",
        component: poollist,
      }, {
        path: "/project/myproject/:userId",
        name: "myproject",
        component: myproject,
      },
      {
        path: "/project/projectdetail/:pid",
        name: "projectdetail",
        component: projectdetail,
      },
      {
        path: "/project/makeproject",
        name: "makeproject",
        component: makeproject,
      }, {
        path: "/qaboard/writeqa",
        name: "writeqa",
        component: writeqa,
      },
      {
        path: "/qaboard/detailqa/:bno",
        name: "detailqa",
        component: detailqa,
      },
      {
        path: "/qaboard/modifyqa/:bno",
        name: "modifyqa",
        component: modifyqa,
      },
      {
        path: "/recruit/recruit1",
        name: "recruit1",
        component: recruit1,
      },
      {
        path: "/recruit/recruitdetail/:rnum",
        name: "recruitdetail",
        component: recruitdetail,
      },
      {
        path: "/recruit/registteam",
        name: "registteam",
        component: registteam,
      }, {
        path: "/signup",
        name: "signup",
        component: signup,
      },
      {
        path: "/emailcheck",
        name: "emailcheck",
        component: emailcheck,
      }, {
        path: "/user/findid",
        name: "findIDPW",
        component: findIDPW,
      },
      {
        path: "/user/editinfo",
        name: "editinfo",
        component: editinfo,
      },
      {
        path: "/user/leave",
        name: "leave",
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
  { path: "*", component: NotFound }
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
