$apps = @(
	"OUTLOOK.EXE"
	"Teams.exe"
)

foreach ($app in $apps) {
	Start-Process $app
}

$websites = @(
	"https://www.facebook.com/"
	"https://www.espn.com/"
	"https://www.scmagazine.com/topic/threats"
	"https://www.bleepingcomputer.com/"
)

foreach ($website in $websites) {
	Start-Process "chrome.exe" -ArgumentList $website -PassThru
}