/*
 * File: app/store/Actions.js
 *
 * This file was generated by Sencha Architect version 2.2.2.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.2.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.2.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Cucgp.store.Actions', {
    extend: 'Ext.data.Store',

    requires: [
        'Cucgp.model.Action'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            model: 'Cucgp.model.Action',
            storeId: 'sActions',
            proxy: {
                type: 'ajax',
                api: {
                    create: 'rest/actions/creates',
                    read: 'rest/actions/reads',
                    update: 'rest/actions/updates',
                    destroy: 'rest/actions/deletes'
                },
                headers: {
                    Accept: 'application/json'
                },
                reader: {
                    type: 'json',
                    root: 'actions'
                }
            },
            listeners: {
                beforeload: {
                    fn: me.onStoreBeforeLoad,
                    scope: me
                }
            }
        }, cfg)]);
    },

    onStoreBeforeLoad: function(store, operation, eOpts) {
        /*
        Ext.Ajax.defaultHeaders = {
        'Accept' : 'application/json'
        };
        */
    }

});