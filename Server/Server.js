// call the packages we need
var express    = require('express');
var bodyParser = require('body-parser');
var app        = express();
var morgan     = require('morgan');
var exec 	   = require('child_process').exec;
var last_time_executed = null;
var timer;

// configure app
app.use(morgan('dev')); // log requests to the console
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var port     = process.env.PORT || 1337; 


// ROUTES FOR OUR API
// =============================================================================

// create our router
var router = express.Router();

// middleware to use for all requests
router.use(function(req, res, next) {
	// do logging
	console.log('Router is booting.');
	next();
});

router.route('/door/open/:seconds')
	.get(function(req, res) {
		var seconds = req.params.seconds;
		
		if (seconds > 30)
		{
			seconds = 30;
		}
		
		console.log("Door will open for " + seconds + " seconds");
		
		clearTimeout(timer);
		timer = setTimeout(function()
		{ 
			door_close();
		}, seconds * 1000);
		door_open();
		 
		res.json({ message:"Opened", door:1 });
	});
	
	
router.route('/door/info')
	.get(function(req, res) {
		exec("cat  doorinfo.txt", 
		function(error, stdout)
		{
			
			var array = stdout.split("\n");
			console.log(array);
			res.json({ message:array});
		});
		 
		
	});
	
function door_open()
{
	exec("./door 'open'", function(err, data) {  
			console.log(err)
			console.log(data.toString());                       
		});  
}

function door_close()
{
	exec("./door 'close'", function(err, data) {  
			console.log(err)
			console.log(data.toString());                       
		});  
}

// REGISTER OUR ROUTES -------------------------------
app.use('/api', router);

// START THE SERVER
// =============================================================================
app.listen(port);
console.log('RingMyBell is running on port: ' + port);
