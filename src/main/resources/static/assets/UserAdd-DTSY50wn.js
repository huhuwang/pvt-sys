import{r as m,k as w,f as h,w as l,n as v,V as c,d as n,o as U,b as e,a as R,q as $}from"./index-BZWBJNRr.js";import{_ as B}from"./LoginOut-GI41O1go.js";const A={__name:"UserAdd",setup(C){const x=$(),p=m(),t=m({account:null,userName:null,nickName:null,password:null,role:{id:null}}),V=m({account:[{required:!0,message:"this field is required"}],userName:[{required:!0,message:"this field is required"}],password:[{required:!0,message:"this field is required"}],role:[{role:!0,message:"this field is required"},{validator(){if(!t.value.role.id)return new Error("this field is required")}}]}),y=async()=>{await v.get("role/list").then(r=>{const a=p.value;if(a){const d=[];for(let o=0;o<r.length;o++)d.push({value:`${r[o].id}`,label:`${r[o].roleName}`});a.reloadData(d)}}).catch(r=>{c.modal.message({content:r,status:"error"})})},b=()=>{v.post("user/add",t.value).then(r=>{c.modal.message({content:"保存成功",status:"success"}),f()}).catch(r=>{c.modal.message({content:"保存失败",status:"error"})})},f=()=>{x.push({name:"home-page",query:{at:"user"}})};return w(()=>{y()}),(r,a)=>{const d=n("vxe-layout-header"),o=n("vxe-input"),u=n("vxe-form-item"),g=n("vxe-select"),_=n("vxe-button"),N=n("vxe-form"),k=n("vxe-layout-body"),q=n("vxe-layout-container");return U(),h(q,{vertical:""},{default:l(()=>[e(d,{fixed:"",style:{"background-color":"#a6c9ed"}},{default:l(()=>[e(B)]),_:1}),e(k,null,{default:l(()=>[R("div",null,[e(N,{vertical:"",data:t.value,rules:V.value,onSubmit:b},{default:l(()=>[e(u,{title:"账号",field:"account",span:"24","item-render":{}},{default:l(i=>[e(o,{modelValue:t.value.account,"onUpdate:modelValue":a[0]||(a[0]=s=>t.value.account=s)},null,8,["modelValue"])]),_:1}),e(u,{title:"用户名",field:"userName",span:"24","item-render":{}},{default:l(i=>[e(o,{modelValue:t.value.userName,"onUpdate:modelValue":a[1]||(a[1]=s=>t.value.userName=s)},null,8,["modelValue"])]),_:1}),e(u,{title:"昵称",field:"nickName",span:"24","item-render":{}},{default:l(i=>[e(o,{modelValue:t.value.nickName,"onUpdate:modelValue":a[2]||(a[2]=s=>t.value.nickName=s)},null,8,["modelValue"])]),_:1}),e(u,{title:"密码",field:"password",span:"24","item-render":{}},{default:l(i=>[e(o,{modelValue:t.value.password,"onUpdate:modelValue":a[3]||(a[3]=s=>t.value.password=s),placeholder:"password",type:"password",clearable:""},null,8,["modelValue"])]),_:1}),e(u,{title:"角色",field:"role",span:"24","item-render":{}},{default:l(i=>[e(g,{ref_key:"selectRef",ref:p,modelValue:t.value.role.id,"onUpdate:modelValue":a[4]||(a[4]=s=>t.value.role.id=s)},null,8,["modelValue"])]),_:1}),e(u,{align:"center",span:"24","item-render":{}},{default:l(()=>[e(_,{type:"submit",status:"primary",content:"提交"}),e(_,{content:"取消",onClick:f})]),_:1})]),_:1},8,["data","rules"])])]),_:1})]),_:1})}}};export{A as default};
