import{r as d,j as h,f as D,w as o,d as r,o as L,b as l,a as W,p,q,n as S,V as I}from"./index-DkAa4VJT.js";import{_ as j}from"./LoginOut-ChvC3TuR.js";const G={__name:"CaseAdd",setup(H){const B=q(),x=d(!1),y=d("0"),R=d("0"),k=d("0"),T=d("0"),b=d("0"),V=d("0"),w=d("0"),g=d("0"),U=h({ticketNo:history.state.ticketNo,ticketId:history.state.ticketId}),v=()=>{const m=Number(y.value),e=Number(R.value),_=Number(k.value),c=Number(T.value),n=Number(b.value),t=Number(V.value),f=Number(w.value),s=Number(g.value);n>0||t>0?x.value=!0:x.value=!1,a.value.category=[],m>0&&a.value.category.push(m),e>0&&a.value.category.push(e),_>0&&a.value.category.push(_),c>0&&a.value.category.push(c),n>0&&a.value.category.push(n),t>0&&a.value.category.push(t),f>0&&a.value.category.push(f),s>0&&a.value.category.push(s)},a=d({ticketNo:U.ticketNo,description:null,summary:null,expectedResult:null,step:null,type:1,priority:8,category:[],rtFlow:null}),A=h({envList:[{validator(){if(a.value.category.length==0)return new Error("this field is required")}}],rtFlow:[{validator(){if((Number(b.value)>0||Number(V.value)>0)&&!a.value.rtFlow)return new Error("this field is required")}}]}),E=()=>{S.post("case/add",a.value).then(m=>{I.modal.message({content:"保存成功",status:"success"}),N()}).catch(m=>{I.modal.message({content:m,status:"error"})})},N=()=>{const m={ticketId:U.ticketId};B.push({name:"case-list",state:m})};return(m,e)=>{const _=r("vxe-layout-header"),c=r("vxe-textarea"),n=r("vxe-form-item"),t=r("vxe-option"),f=r("vxe-select"),s=r("vxe-checkbox"),C=r("vxe-button"),P=r("vxe-form"),z=r("vxe-layout-body"),F=r("vxe-layout-container");return L(),D(F,{vertical:""},{default:o(()=>[l(_,{fixed:"",style:{"background-color":"#a6c9ed"}},{default:o(()=>[l(j)]),_:1}),l(z,null,{default:o(()=>[W("div",null,[l(P,{vertical:"",data:a.value,onSubmit:E,rules:A},{default:o(()=>[l(n,{title:"description",field:"description",span:"24","item-render":{}},{default:o(u=>[l(c,{modelValue:a.value.description,"onUpdate:modelValue":e[0]||(e[0]=i=>a.value.description=i),placeholder:"case description",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),l(n,{title:"summary",field:"summary",span:"24","item-render":{}},{default:o(()=>[l(c,{modelValue:a.value.summary,"onUpdate:modelValue":e[1]||(e[1]=u=>a.value.summary=u),placeholder:"case summary",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),l(n,{title:"expected result",field:"expectedResult",span:"24","item-render":{}},{default:o(u=>[l(c,{modelValue:a.value.expectedResult,"onUpdate:modelValue":e[2]||(e[2]=i=>a.value.expectedResult=i),placeholder:"expected result",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),l(n,{title:"step",field:"step",span:"24","item-render":{}},{default:o(u=>[l(c,{modelValue:a.value.step,"onUpdate:modelValue":e[3]||(e[3]=i=>a.value.step=i),placeholder:"step",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),l(n,{title:"Priority",field:"priority",span:"24","item-render":{}},{default:o(u=>[l(f,{modelValue:a.value.priority,"onUpdate:modelValue":e[4]||(e[4]=i=>a.value.priority=i),placeholder:"priority",size:"medium"},{default:o(()=>[l(t,{value:1,label:"Urgent"}),l(t,{value:2,label:"Highest"}),l(t,{value:3,label:"High"}),l(t,{value:4,label:"Low"}),l(t,{value:5,label:"Lowest"}),l(t,{value:6,label:"Blocker"}),l(t,{value:7,label:"Critical"}),l(t,{value:8,label:"Major"}),l(t,{value:9,label:"Minor"}),l(t,{value:10,label:"Trivial"})]),_:1},8,["modelValue"])]),_:1}),l(n,{title:"适用场景",field:"envList",span:"24","item-render":{}},{default:o(()=>[l(s,{modelValue:y.value,"onUpdate:modelValue":e[5]||(e[5]=u=>y.value=u),"checked-value":"17","unchecked-value":"0",onChange:v},{default:o(()=>e[14]||(e[14]=[p("SIT-WEB")])),_:1},8,["modelValue"]),l(s,{modelValue:R.value,"onUpdate:modelValue":e[6]||(e[6]=u=>R.value=u),"checked-value":"33","unchecked-value":"0",onChange:v},{default:o(()=>e[15]||(e[15]=[p("SIT-IPAD")])),_:1},8,["modelValue"]),l(s,{modelValue:k.value,"onUpdate:modelValue":e[7]||(e[7]=u=>k.value=u),"checked-value":"18","unchecked-value":"0",onChange:v},{default:o(()=>e[16]||(e[16]=[p("UAT-WEB")])),_:1},8,["modelValue"]),l(s,{modelValue:T.value,"onUpdate:modelValue":e[8]||(e[8]=u=>T.value=u),"checked-value":"34","unchecked-value":"0",onChange:v},{default:o(()=>e[17]||(e[17]=[p("UAT-IPAD")])),_:1},8,["modelValue"]),l(s,{modelValue:b.value,"onUpdate:modelValue":e[9]||(e[9]=u=>b.value=u),"checked-value":"20","unchecked-value":"0",onChange:v},{default:o(()=>e[18]||(e[18]=[p("RT-WEB")])),_:1},8,["modelValue"]),l(s,{modelValue:V.value,"onUpdate:modelValue":e[10]||(e[10]=u=>V.value=u),"checked-value":"36","unchecked-value":"0",onChange:v},{default:o(()=>e[19]||(e[19]=[p("RT-IPAD")])),_:1},8,["modelValue"]),l(s,{modelValue:w.value,"onUpdate:modelValue":e[11]||(e[11]=u=>w.value=u),"checked-value":"24","unchecked-value":"0",onChange:v},{default:o(()=>e[20]||(e[20]=[p("PVT-WEB")])),_:1},8,["modelValue"]),l(s,{modelValue:g.value,"onUpdate:modelValue":e[12]||(e[12]=u=>g.value=u),"checked-value":"40","unchecked-value":"0",onChange:v},{default:o(()=>e[21]||(e[21]=[p("PVT-IPAD")])),_:1},8,["modelValue"])]),_:1}),l(n,{title:"RT模板",field:"rtFlow",span:"24","item-render":{},visible:x.value},{default:o(u=>[l(f,{modelValue:a.value.rtFlow,"onUpdate:modelValue":e[13]||(e[13]=i=>a.value.rtFlow=i),placeholder:"RT模板",size:"medium"},{default:o(()=>[l(t,{value:"RT1",label:"RT1"}),l(t,{value:"RT2",label:"RT2"}),l(t,{value:"RT3",label:"RT3"}),l(t,{value:"RT4",label:"RT4"}),l(t,{value:"RT5",label:"RT5"}),l(t,{value:"RT6",label:"RT6"}),l(t,{value:"RT7",label:"RT7"}),l(t,{value:"RT8",label:"RT8"})]),_:1},8,["modelValue"])]),_:1},8,["visible"]),l(n,{align:"center",span:"24","item-render":{}},{default:o(()=>[l(C,{type:"submit",status:"primary",content:"提交"}),l(C,{content:"取消",onClick:N})]),_:1})]),_:1},8,["data","rules"])])]),_:1})]),_:1})}}};export{G as default};