Ext.define('Cucgp.view.Login', {
  extend : 'Ext.form.Panel',
  requires : ['Cucgp.view.Loader'],
  alias : 'widget.Login',
  id : 'idLogin',
  title : '中国传媒大学毕设平台',
  layout : {
    type : 'vbox',
    align : 'center'
  },
  width : '33%',
  items : [ {
    xtype : 'label',
    flex : 1,
    text : '用户名'
  }, {
    xtype : 'textfield',
    flex : 1,
    name : 'userName'
  }, {
    xtype : 'label',
    flex : 1,
    text : '密码'
  }, {
    xtype : 'textfield',
    flex : 1,
    inputType : 'password',
    name : 'password'
    } ],
    buttons : [ {
      text : '登录',
      formBind : true, // only enabled once the form is valid
      disabled : true,
      handler : function () {
        var form = this.up('form').getForm();
        var password = form.findField('password').getValue();
        var encrypt = hex_md5(password);
        form.findField('password').setValue(encrypt);
        if (form.isValid()) {
          Ext.Ajax.request({
            url : 'rest/auth/login',
            headers : {
              "Accept" : "application/json"
            },
            method : 'POST',
            params : {
              'userName' : form.findField('userName').getValue(),
              'password' : form.findField('password').getValue()
            },
            success : function(response, opts) {
              var t = JSON.parse(response.responseText);
              if (t.status === "-1") {
                Ext.Msg.alert("登录失败！" + t.msg);
                return;
              } else if (t.status === "0") {
                viewport = Ext.getCmp('idViewport');
                viewport.removeAll();
                loader = Ext.create('Cucgp.view.Loader');
                loader.myInit();
                viewport.insert(loader);
                return;
              }
            },
            failure : function(response, opts) {
              var t = JSON.parse(response.responseText);
              Ext.Msg.alert("登录失败！ErrorMsg:" + t.msg);
              return;
            }
          });
        }
        return;
      } 
      }],
      initComponent: function() {
        var me = this;
        MVC.v.Login = me;
        if (debug) {
          console.log('Cucgp.view.login init ...');
        }
        me.callParent(arguments);
      }
    });
