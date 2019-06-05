const root='http://127.0.0.1:8085'

const Api={
  "login":(username,password)=>`${root}/doctor/login?username=${username}&password=${password}`,
  'register':(username,password,dName,gender,phone,positionTime)=>`${root}/doctor/register?username=${username}&password=${password}&dName=${dName}&gender=${gender}&phone=${phone}&positionTime=${positionTime}`,
  'getDoctor':(username)=>`${root}/doctor/information?username=${username}`,
  'updateDoctor':(username,password,birthday,dName,phone)=>`${root}/doctor/update?username=${username}&password=${password}&birthday=${birthday}&dName=${dName}&phone=${phone}`,

  'getAdvise':(username)=>`${root}/doctor/advise?dNo=${username}`,
  'sendAdvise':(id,advise)=>`${root}/doctor/send_advise?advise=${advise}&id=${id}`,
  'doctorList':()=>`${root}/doctor/list`,
  'getHandledCases':(dusername)=>`${root}/doctor/handling?dusername=${dusername}`,

  //病患
  'plogin':(username,password)=>`${root}/patient/login?username=${username}&password=${password}`,
  'pregister':(username,password,gender,birthday,pName,phone,parent)=>`${root}/patient/register?username=${username}&password=${password}&gender=${gender}&birthday=${birthday}&pName=${pName}&phone=${phone}&parent=${parent}`,
  'getPatient':(username)=>`${root}/patient?username=${username}`,
  'updatePatient':(username,pName,gender,birthday,parent,phone)=>`${root}/patient/update?username=${username}&pName=${pName}&gender=${gender}&birthday=${birthday}&parent=${parent}&phone=${phone}`,

  //疫苗
  'getVaccines':()=>`${root}/vaccines`,
  'searchVaccine':(vName)=>`${root}/vaccine?vName=${vName}`,
  'insertVaccine':(vName,pathogenesis,precaution,handling)=>`${root}/vaccine/insert?vName=${vName}&pathogenesis=${pathogenesis}&precaution=${precaution}&handling=${handling}`,
  'modifiedVaccine':(vid,vName,pathogenesis,precaution,handling)=>`${root}/vaccine/modified?vid=${vid}&vName=${vName}&pathogenesis=${pathogenesis}&precaution=${precaution}&handling=${handling}`,
  //预约
  'buildCase':(pusername,dusername,describe)=>`${root}/patient/creat_case?pNo=${pusername}&dNo=${dusername}&describe=${describe}`,
  'addPAppointmentToDoctor':(cNo,aTime)=>`${root}/appointment/doctor?cNo=${cNo}&aTime=${aTime}`,
  'addPAppointmentToVaccine':(cNo,aTime,vNo)=>`${root}/appointment/vaccine?cNo=${cNo}&aTime=${aTime}&vNo=${vNo}`,
  'modifiedTime':(aTime,aId)=>`${root}/modifyTime?aTime=${aTime}&aId=${aId}`,
  'getPatientAppointment':(username)=>`${root}/patientAppointment?pusername=${username}`,
  'doctorAppointmentList':(username)=>`${root}/doctorAppointmentTime?dusername=${username}`,
  'delAppointment':(aid)=>`${root}/appointment/del?aId=${aid}`,
  'currentDayAppointment':(dusername)=>`${root}/doctor/current?dusername=${dusername}`
};
export default Api;
