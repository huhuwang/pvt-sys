import{r as d,j as g,f as L,w as a,d as n,o as q,b as e,a as A,p,e as P,q as j,n as D,V as U}from"./index-BZWBJNRr.js";import{_ as H}from"./LoginOut-GI41O1go.js";const $={__name:"CaseAdd",setup(M){const C=j(),f=d("0"),b=d("0"),i=d("0"),x=d("0"),V=d("0"),_=d("0");let R=d(!1);const k=g({ticketNo:history.state.ticketNo,ticketId:history.state.ticketId}),m=()=>{u.value.category=Number(f.value)+Number(b.value)+Number(i.value)+Number(x.value)+Number(V.value)+Number(_.value),Number(i.value)>0?R.value=!0:R.value=!1},u=d({ticketNo:k.ticketNo,description:null,summary:null,expectedResult:null,type:1,priority:8,rtFlow:null}),h=g({envList:[{validator(){if(Number(f.value)+Number(b.value)+Number(i.value)+Number(x.value)<=0)return new Error("this field is required")}}],device:[{validator(){if(Number(V.value)+Number(_.value)<=0)return new Error("this field is required")}}],rtFlow:[{validator(){if(Number(i.value)>0&&!u.value.rtFlow)return new Error("this field is required")}}]}),I=()=>{D.post("case/add",u.value).then(c=>{U.modal.message({content:"保存成功",status:"success"}),T()}).catch(c=>{U.modal.message({content:c,status:"error"})})},T=()=>{const c={ticketNo:k.ticketNo,ticketId:k.ticketId};C.push({name:"case-list",state:c})};return(c,l)=>{const F=n("vxe-layout-header"),y=n("vxe-textarea"),r=n("vxe-form-item"),t=n("vxe-option"),N=n("vxe-select"),v=n("vxe-checkbox"),w=n("vxe-button"),z=n("vxe-form"),B=n("vxe-layout-body"),E=n("vxe-layout-container");return q(),L(E,{vertical:""},{default:a(()=>[e(F,{fixed:"",style:{"background-color":"#a6c9ed"}},{default:a(()=>[e(H)]),_:1}),e(B,null,{default:a(()=>[A("div",null,[e(z,{vertical:"",data:u.value,onSubmit:I,rules:h},{default:a(()=>[e(r,{title:"description",field:"description",span:"24","item-render":{}},{default:a(o=>[e(y,{modelValue:u.value.description,"onUpdate:modelValue":l[0]||(l[0]=s=>u.value.description=s),placeholder:"case description",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),e(r,{title:"summary",field:"summary",span:"24","item-render":{}},{default:a(()=>[e(y,{modelValue:u.value.summary,"onUpdate:modelValue":l[1]||(l[1]=o=>u.value.summary=o),placeholder:"case summary",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),e(r,{title:"expected result",field:"expectedResult",span:"24","item-render":{}},{default:a(o=>[e(y,{modelValue:u.value.expectedResult,"onUpdate:modelValue":l[2]||(l[2]=s=>u.value.expectedResult=s),placeholder:"expected result",autosize:{minRows:2,maxRows:10}},null,8,["modelValue"])]),_:1}),e(r,{title:"Priority",field:"priority",span:"24","item-render":{}},{default:a(o=>[e(N,{modelValue:u.value.priority,"onUpdate:modelValue":l[3]||(l[3]=s=>u.value.priority=s),placeholder:"priority",size:"medium"},{default:a(()=>[e(t,{value:1,label:"Urgent"}),e(t,{value:2,label:"Highest"}),e(t,{value:3,label:"High"}),e(t,{value:4,label:"Low"}),e(t,{value:5,label:"Lowest"}),e(t,{value:6,label:"Blocker"}),e(t,{value:7,label:"Critical"}),e(t,{value:8,label:"Major"}),e(t,{value:9,label:"Minor"}),e(t,{value:10,label:"Trivial"})]),_:1},8,["modelValue"])]),_:1}),e(r,{title:"适用场景",field:"envList",span:"24","item-render":{}},{default:a(()=>[e(v,{modelValue:f.value,"onUpdate:modelValue":l[4]||(l[4]=o=>f.value=o),"checked-value":"1","unchecked-value":"0",onChange:m},{default:a(()=>l[11]||(l[11]=[p("SIT")])),_:1},8,["modelValue"]),e(v,{modelValue:b.value,"onUpdate:modelValue":l[5]||(l[5]=o=>b.value=o),"checked-value":"2","unchecked-value":"0",onChange:m},{default:a(()=>l[12]||(l[12]=[p("UAT")])),_:1},8,["modelValue"]),e(v,{modelValue:i.value,"onUpdate:modelValue":l[6]||(l[6]=o=>i.value=o),"checked-value":"4","unchecked-value":"0",onChange:m},{default:a(()=>l[13]||(l[13]=[p("RT")])),_:1},8,["modelValue"]),e(v,{modelValue:x.value,"onUpdate:modelValue":l[7]||(l[7]=o=>x.value=o),"checked-value":"8","unchecked-value":"0",onChange:m},{default:a(()=>l[14]||(l[14]=[p("PVT")])),_:1},8,["modelValue"])]),_:1}),e(r,{title:"设备",field:"device",span:"24","item-render":{}},{default:a(()=>[e(v,{modelValue:V.value,"onUpdate:modelValue":l[8]||(l[8]=o=>V.value=o),"checked-value":"16","unchecked-value":"0",onChange:m},{default:a(()=>l[15]||(l[15]=[p("WEB")])),_:1},8,["modelValue"]),e(v,{modelValue:_.value,"onUpdate:modelValue":l[9]||(l[9]=o=>_.value=o),"checked-value":"32","unchecked-value":"0",onChange:m},{default:a(()=>l[16]||(l[16]=[p("IPAD")])),_:1},8,["modelValue"])]),_:1}),e(r,{title:"RT模板",field:"rtFlow",span:"24","item-render":{},visible:P(R)},{default:a(o=>[e(N,{modelValue:u.value.rtFlow,"onUpdate:modelValue":l[10]||(l[10]=s=>u.value.rtFlow=s),placeholder:"RT模板",size:"medium"},{default:a(()=>[e(t,{value:"RT1",label:"RT1"}),e(t,{value:"RT2",label:"RT2"}),e(t,{value:"RT3",label:"RT3"}),e(t,{value:"RT4",label:"RT4"}),e(t,{value:"RT5",label:"RT5"}),e(t,{value:"RT6",label:"RT6"}),e(t,{value:"RT7",label:"RT7"}),e(t,{value:"RT8",label:"RT8"})]),_:1},8,["modelValue"])]),_:1},8,["visible"]),e(r,{align:"center",span:"24","item-render":{}},{default:a(()=>[e(w,{type:"submit",status:"primary",content:"提交"}),e(w,{content:"取消",onClick:T})]),_:1})]),_:1},8,["data","rules"])])]),_:1})]),_:1})}}};export{$ as default};
