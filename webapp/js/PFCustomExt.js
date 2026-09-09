var PFCustomExt = function () {
};

PFCustomExt.prototype.disableFocusCalendar = function () {
	PrimeFaces.widget.Dialog.prototype.applyFocus = function() {
	  var firstInput = this.jq.find(':not(:submit):not(:button):input:visible:enabled:first');
	  if(!firstInput.hasClass('hasDatepicker')) {
	      firstInput.focus();
	  }
	};
};

PFCustomExt.prototype.disableExpandedRowSelectEvent = function () {
	//Special override function to ensure rowExpansion is not CLICKABLE 
	PrimeFaces.widget.DataTable.prototype.bindSelectionEvents = function() {
		var a = this;
		this.rowSelector = this.jqId
	    	+ " tbody.ui-datatable-data > tr.ui-widget-content:not(.ui-datatable-empty-message):not(.ui-expanded-row-content)";
 
		$(document).off("click.datatable", this.rowSelector)
	   	.on("click.datatable", this.rowSelector, null, function(b) {
	   		a.onRowClick(b, this);
	   	});
		if (!this.cfg.columnSelectionMode) {
		    this.bindRowHover();
	      if (this.cfg.selectionMode) {
	         if (this.cfg.columnSelectionMode == "single") {
	            this.bindRadioEvents();
	         } else {
	            this.bindCheckboxEvents();
	         }
	      }
	   }
	   if (this.hasBehavior("rowDblselect")) {
	      $(document).off("dblclick.datatable", this.rowSelector)
	      	.on("dblclick.datatable", this.rowSelector, null, function(b) {
	      		a.onRowDblclick(b, $(this));
	      	});
	   }
	};
};


PFCustomExt.prototype.resetDataTable = function (dataTableObj) {
	if (typeof dataTableObj != "undefined") {
		dataTableObj.clearFilters();
		dataTableObj.unselectAllRows();
	}
	if (typeof dataTableObj != "undefined") {
		dataTableObj.clearFilters();
		dataTableObj.unselectAllRows();
	}
};


var pfCustExt = new PFCustomExt();