import Vue from 'vue'
import Router from 'vue-router'

import FirstContent from '@/components/home/FirstContent'
//公众版模块
import CommonPublic from '../components/home/CommonPublic'
import GeneralIndex from '@/components/content/GeneralIndex'
import PatientServer from '@/components/content/PatientServer'
import HealthNote from '@/components/content/HealthNote'

import TestContent from '@/components/home/TestContent'
//病患
import Register from '@/components/patient/Dailog'
import AppointmentPList from '@/components/patient/AppointmentList'

//员工模块
import DoctorLogin from '@/components/doctor/DoctorLogin'
import Doctor from '@/components/doctor/Doctor'

import AdminIndex from '@/components/doctor_admin/AdminIndex'
import DoctorList from '@/components/doctor_admin/DoctorList'
import AddMembers from '@/components/doctor_admin/AddMembers'
import AddHealthNotes from '@/components/doctor_admin/AddHealthNotes'
//二级路由
import Information from '@/components/doctor/Information'
import Advise from '../components/doctor/Advise'
import AppointmentList from '@/components/doctor/AppointmentList'
import HandledCases from '@/components/doctor/HandledCases'

Vue.use(Router);

export default new Router({
  routes: [
    {path:'/',redirect:'/index'},
    {path: '/index', name: 'index', component: FirstContent},
    {path:'/public',component:CommonPublic,children: [
        {path:'/general',name:'general',component:GeneralIndex},
        {path:'/server',name:'server',component:PatientServer},
        {path:'/note',name:'note',component:HealthNote},
        {path:'/list',name:'p_list',component:AppointmentPList}
      ]},
    {path:'/login',component:DoctorLogin,name:'login'},
    {path:'/doctor',component:Doctor,children:[
        {path:'/doctor/info',component:Information,name:'info'},
        {path:'/doctor/advise',component:Advise,name:'advise'},
        {path:'/doctor/appointment',component:AppointmentList,name:'dappointment'},
        {path:'/doctor/handled',component:HandledCases,name:'handled'},
      ]},
    {path:'/admin',component:AdminIndex,name:'admin',children:[
        {path:'/admin/d_list',component:DoctorList,name:'d_list'},
        {path:'/admin/add',component:AddMembers,name:'d_add'},
        {path:'/admin/addNotes',component:AddHealthNotes,name:'d_addNotes'}
      ]},
    {path:'/p_register',component:Register,name:'p_register'},
    {path:'/test',component:TestContent,name:'test'}
  ],
  mode:'history'
})
