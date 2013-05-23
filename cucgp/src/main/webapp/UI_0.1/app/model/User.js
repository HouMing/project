Ext.define('Cucgp.model.User', {
  extend : 'Ext.data.Model',
  idProperty : 'userId',
  fields : [{
    name : 'userId',
    type : 'int',
    useNull : false
  }, {
    name : 'groupId',
    type : 'int',
    useNull " false
  }, 'userName', 'password', 'userHome'],
  proxy : {
    type: 'ajax',
    api: {
      create : '/admin/user/create',
      read : '/admin/user/read',
      update : '/admin/user/update',
      destroy : '/admin/user/destroy'
    }
  },
  headers : {
    'Accept' : 'application/json'
  },
  reader : {
    type : 'json',
    root : 'user',
    successProperty : 'success'
  }
});
