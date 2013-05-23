Ext.define('Cucgp.model.Action', {
  extend : 'Ext.data.Model',
  idProperty : 'actionId', 
  fields : [ {
    name : 'actionId',
    type : 'int',
    useNull : false
  }, {
    name : 'roleId',
    type : 'int',
    useNull : false
  }, 'actionName', 'actionUrl', 'description' ]
});
