(function($) {
    jQuery.fn.formloader = function(parameters) {
        var form = $(this);
        var formFields = form.find("input[name],select[name],textarea[name]");
        formFields.each(function() {
            var name = this.name;
            var value = jQuery.proxy(function() {
                try {
                    return eval('this.' + name);
                } catch (e) {
                    return "";
                }
            }, parameters.data)();
            $("input[name=\"" + name + "\"][type=text]", form).val(value);
            $("input[name=\"" + name + "\"][type=hidden]", form).val(value);
            $("input[name=\"" + name + "\"][type=password]", form).val(value);
            //$("input[name=\"" + name + "\"][type=radio]", form).val(value);
            $("textarea[name=\"" + name + "\"]", form).val(value);
            $("select[name=\"" + name + "\"]", form).val(value);
            if($("input[name=\"" + name + "\"][type=radio]").length > 0 || $("input[name=\"" + name + "\"][type=checkbox]").length > 0){
               setCheckVal(name, value);
            }
            if ($.fn.typeahead) {
                $("input[name=\"" + name + "\"][type=text]", form).typeahead('val', value);
            }
        });
        if (parameters.onSuccess && typeof(parameters.onSuccess) === "function") {
            parameters.onSuccess.call();
        }

        function setCheckVal(key, vals) {
            var rr = form.find("input[name=\"" + key + "\"][value='"+vals+"'][type=radio], input[name=\"" + key + "\"][value='"+vals+"'][type=checkbox]");
            if (rr.length > 0) {
                rr.prop('checked',function () {
                    return vals;
                })
            }
        }
        return form;
    };
})(jQuery);