$currentVersion = "8.16.1"
$oldVersion = "8.11.4"
$serverFolder = "\\<shared-folder>\Applications\CND\Elastic\elastic-agent-$currentVersion-windows-x86_64\
$softwareInstaller = "elastic-agent.exe"
$localTempFolder = "C:\Windows\Temp"
$installed = "C:\Program Files\Elastic\Agent\data\elastic-agent-$currentVersion-*"
$agentPath = "C:\Program Files\Elastic\Agent"

# Test if current version is installed
if (-Not (Test-Path -Path $installed)) {

  if (Get-Service -Name 'Elastic Agent') {
    Stop-Service -Name 'Elastic Agent' -Force
    Remove-Item -Path $agentPath -Recurse

    Copy-Item -Path "$serverFolder\$softwareInstaller" -Destination $localTempFolder -Force

    if (Test-Path "$localTempFolder\$softwareInstaller"){
      & ".\$localTempFolder\$softwareInstaller64" install --url=https://{fleet-server}:8220 --enrollment-token={ enrollment-token } -i
    }

    Remove-Item -Path $localTempFolder\$softwareInstaller -Force
  } else {

    Copy-Item -Path "$serverFolder\$softwareInstaller" -Destination $localTempFolder -Force

    if (Test-Path "$localTempFolder\$softwareInstaller"){
     & ".\$localTempFolder\$softwareInstaller64" install --url=https://{fleet-server}:8220 --enrollment-token={ enrollment-token } -i
    }

    Remove-Item -Path $localTempFolder\$softwareInstaller -Force