Ext.define('Cucgp.store.Actions', {
  extend: 'Ext.data.Store',
  storeId : 'Actions',
  requires: ['Cucgp.model.Action'],
  model: 'Cucgp.model.Action',
  autoLoad: true,
  proxy: {
  type: 'ajax',
  url: 'rest/actions/lsActions',
  headers : {
  'Accept' : 'application/json'
  },
  reader: {
    type: 'json',
    root: 'actions',
    successProperty: 'success'
    }
  }
});
