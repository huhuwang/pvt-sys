import{r as n,j as H,k as M,f as $,w as a,n as R,V as f,d,o as j,b as t,a as _,p as c,s as q}from"./index-DkAa4VJT.js";import{_ as z}from"./LoginOut-ChvC3TuR.js";const L={style:{"text-align":"center"}},W={style:{"text-align":"center"}},K={__name:"RelatedBaseCase",setup(F){const g=n(!1),U=n(),x=n();let p=new Set;const b=H({env:0,device:0}),N=history.state.deploymentId,y=n("0"),k=n("0"),h=n("0"),V=n("0"),C=n("0"),w=n("0"),I=async()=>{g.value=!0;let l=n({});const{env:e,device:s}=b;l.value.deploymentId=N,l.value.env=e,l.value.device=s,await R.post("case/relate/query",l.value).then(u=>{U.value=u}).catch(u=>{f.modal.message({content:u,status:"error"})}).finally(()=>{g.value=!1})},B=l=>{switch(l.priority){case 1:return"Urgent";case 2:return"Highest";case 3:return"High";case 4:return"Low";case 5:return"Lowest";case 6:return"Blocker";case 7:return"Critical";case 8:return"Major";case 9:return"Minor";case 10:return"Trivial"}},S=({checked:l})=>{const e=x.value;if(e){const s=e.getCheckboxRecords();if(s.length>0){let u=s.map(m=>m.id);p=new Set(u)}}},T=({checked:l})=>{const e=x.value;if(e){const s=e.getCheckboxRecords();if(s.length>0){let u=s.map(m=>m.id);p=new Set(u)}}},D=()=>{if(p.size<=0)f.modal.message({content:"请选择关联的case",status:"error"});else{let l=n({});l.value.deploymentId=N,l.value.caseIds=[...p],R.post("case/relate/add",l.value).then(e=>{f.modal.message({content:"保存成功",status:"success"}),I()}).catch(e=>{f.modal.message({content:e,status:"error"})})}},v=()=>{b.env=Number(y.value)+Number(k.value)+Number(h.value)+Number(V.value),b.device=Number(C.value)+Number(w.value),I()};return M(()=>{I()}),(l,e)=>{const s=d("vxe-layout-header"),u=d("vxe-button"),m=d("vxe-toolbar"),i=d("vxe-checkbox"),r=d("vxe-column"),A=d("vxe-table"),P=d("vxe-layout-body"),E=d("vxe-layout-container");return j(),$(E,{vertical:""},{default:a(()=>[t(s,{fixed:"",style:{"background-color":"#a6c9ed"}},{default:a(()=>[t(z)]),_:1}),t(P,null,{default:a(()=>[_("div",null,[t(m,null,{buttons:a(()=>[t(u,{status:"primary",onClick:D},{default:a(()=>e[6]||(e[6]=[c("确认")])),_:1}),t(u,{"router-link":{name:"home-page"}},{default:a(()=>e[7]||(e[7]=[c("返回")])),_:1})]),_:1}),_("div",L,[t(i,{modelValue:y.value,"onUpdate:modelValue":e[0]||(e[0]=o=>y.value=o),"checked-value":"1","unchecked-value":"0",onChange:v},{default:a(()=>e[8]||(e[8]=[c("SIT")])),_:1},8,["modelValue"]),t(i,{modelValue:k.value,"onUpdate:modelValue":e[1]||(e[1]=o=>k.value=o),"checked-value":"2","unchecked-value":"0",onChange:v},{default:a(()=>e[9]||(e[9]=[c("UAT")])),_:1},8,["modelValue"]),t(i,{modelValue:h.value,"onUpdate:modelValue":e[2]||(e[2]=o=>h.value=o),"checked-value":"4","unchecked-value":"0",onChange:v},{default:a(()=>e[10]||(e[10]=[c("RT")])),_:1},8,["modelValue"]),t(i,{modelValue:V.value,"onUpdate:modelValue":e[3]||(e[3]=o=>V.value=o),"checked-value":"8","unchecked-value":"0",onChange:v},{default:a(()=>e[11]||(e[11]=[c("PVT")])),_:1},8,["modelValue"])]),_("div",W,[t(i,{modelValue:C.value,"onUpdate:modelValue":e[4]||(e[4]=o=>C.value=o),"checked-value":"16","unchecked-value":"0",onChange:v},{default:a(()=>e[12]||(e[12]=[c("WEB")])),_:1},8,["modelValue"]),t(i,{modelValue:w.value,"onUpdate:modelValue":e[5]||(e[5]=o=>w.value=o),"checked-value":"32","unchecked-value":"0",onChange:v},{default:a(()=>e[13]||(e[13]=[c("IPAD")])),_:1},8,["modelValue"])]),t(A,{border:"",ref_key:"tableRef",ref:x,"column-config":{resizable:!0},height:"800",loading:g.value,"row-config":{isCurrent:!0,isHover:!0},data:U.value,onCheckboxAll:S,onCheckboxChange:T},{default:a(()=>[t(r,{type:"checkbox",width:"60"}),t(r,{title:"description",field:"description"}),t(r,{title:"summary",field:"summary"}),t(r,{title:"expected result",field:"expectedResult"}),t(r,{title:"step",field:"step"}),t(r,{title:"priority",field:"priority"},{default:a(({row:o})=>[_("span",null,q(B(o)),1)]),_:1}),t(r,{title:"creater",field:"createUser"}),t(r,{title:"updater",field:"updateUser"}),t(r,{title:"env",field:"env"})]),_:1},8,["loading","data"])])]),_:1})]),_:1})}}};export{K as default};