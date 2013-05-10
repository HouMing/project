Ext.define('Cucgp.view.LoginForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.LoginForm',
    title: '中国传媒大学毕设平台',
    layout: {
      type: 'vbox',
      align: 'center'
    },
    width: '33%',
    items: [
      {
        xtype: 'label',
        flex: 1,
        text: '用户名'
      }, {
        xtype: 'textfield',
        flex: 1,
        name: 'userName'
      }, {
        xtype: 'label',
        flex: 1,
        text: '密码'
      }, {
        xtype: 'textfield',
        flex: 1,
        inputType: 'password',
        name: 'password'
      }
      ],
      buttons: [{
          text: '登录',
          formBind: true, //only enabled once the form is valid
          disabled: true,
          handler: function() {
            var form = this.up('form').getForm();
            var password = form.findField('password').getValue();
            var encrypt = hex_md5(password); 
            form.findField('password').setValue(encrypt);
            if (form.isValid()) {
              Ext.Ajax.request({
                  url: 'rest/auth/login',
                  method: 'POST',

              });
              form.submit({
                  url: 'rest/auth/login',
                  method: 'POST',
                  waitTitle: '登录中...',
                  success: function(form, action) {
                    console.info('Success' + action.result);
                  },
                  failure: function(form, action) {
                    console.dir(action);
                    Ext.Msg.alert('登录失败!');
                  }
              });
            }
          }
        }
      ]
  });
