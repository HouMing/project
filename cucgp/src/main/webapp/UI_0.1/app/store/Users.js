Ext.define('Cucgp.store.Users', {
  extend: 'Ext.data.Store',
  storeId : 'Users',
  requires : ['Cucgp.model.User'],
  model: 'Cucgp.model.User',
  autoLoad: true,
  proxy: {
    type : 'ajax',
    actionMethods: {
      create: 'POST', read: 'GET', update: 'POST', destroy: 'DELETE'
    },
    api: {
      create : 'rest/users/creates',
      read : 'rest/users/reads',
      update : 'rest/users/updates',
      destroy : 'rest/users/deletes'
    },
    headers : {
      'Accept' : 'application/json'
    },
    reader: {
      type: 'json',
      root: 'users',
      successProperty: 'success'
    },
    writer : {
      type : 'json'
    }
  },listeners: {
    write: function(store, operation){
      var record = operation.getRecords()[0],
      name = Ext.String.capitalize(operation.action),
      verb;
      if (name == 'Destroy') {
        record = operation.records[0];
        verb = 'Destroyed';
      } else {
        verb = name + 'd';
      }
      Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
    }
  }
});
