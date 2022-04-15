(function($) {

	var app = $.sammy('#app', function() {
		this.use('Template');

		// this.around(function(callback) {
		// var context = this;
		// this.load('data/articles.json')
		// .then(function(items) {
		// context.items = items;
		// })
		// .then(callback);
		// });

		this.get('#/', function(context) {
			context.app.swap('');
			$.each(this.items, function(i, item) {
				context.render('templates/article.template', {
					id : i,
					item : item
				}).appendTo(context.$element());
			});
		});

		this.get('#/sample-spa/', function(context) {
			_startLoading();
			var str = location.href.toLowerCase();
			context.app.swap('');
			context.render('../../../admin/v1/view/basicwebspa/', {}).appendTo(
					context.$element());
			_finishLoading();
		});

		this.get('#/user/', function(context) {
			_startLoading();
			var str = location.href.toLowerCase();
			context.app.swap('');
			context.render('../../../admin/v1/view/user/', {}).appendTo(
					context.$element());
			_finishLoading();
		});

		this.get('#/authorization/', function(context) {
			_startLoading();
			var str = location.href.toLowerCase();
			context.app.swap('');
			// ../../../admin/v1/view/main#/authorization/
			// context.render('/admin/v1/spa/templates/authorization-index.html',
			// {})
			// .appendTo(context.$element());
			context.render('../../../admin/v1/view/authorization/', {})
					.appendTo(context.$element());
			_finishLoading();
		});

		this.get('#/pos/', function(context) {
        			_startLoading();
        			var str = location.href.toLowerCase();
        			context.app.swap('');
        			context.render('../../../admin/v1/view/pos/', {})
        					.appendTo(context.$element());
        			_finishLoading();
        		});

        this.get('#/daftar-dpo/', function(context) {
                            _startLoading();
                            var str = location.href.toLowerCase();
                            context.app.swap('');
                            context.render('../../../admin/v1/view/daftar/dpo/', {})
                                    .appendTo(context.$element());
                            _finishLoading();
                        });

		this.get('#/usergroup/', function(context) {
			_startLoading();
			var str = location.href.toLowerCase();
			context.app.swap('');
			context.render('../../../admin/v1/view/usergroup/', {}).appendTo(
					context.$element());
			_finishLoading();
		});

		this.get('#/menu/', function(context) {
			_startLoading();
			var str = location.href.toLowerCase();
			context.app.swap('');
			context.render('../../../admin/v1/view/menu/', {}).appendTo(
					context.$element());
			_finishLoading();
		});

		this.get('#/article/:id', function(context) {
			this.item = this.items[this.params['id']];
			if (!this.item) {
				return this.notFound();
			}
			this.partial('templates/article-detail.template');
		});

		this.before('.*', function() {

			var hash = document.location.hash;
			$("nav").find("a").removeClass("current");
			$("nav").find("a[href='" + hash + "']").addClass("current");
		});

	});

	$(function() {
		app.run('#/');
	});

})(jQuery);