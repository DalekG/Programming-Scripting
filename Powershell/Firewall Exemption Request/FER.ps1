Add-Type -AssemblyName System.Windows.Forms

# Create Form
$form = New-Object System.Windows.Forms.Form
$form.Text = "Cyber 9-Line Report"
$form.Size = New-Object System.Drawing.Size(500, 600)
$form.StartPosition = "CenterScreen"

# Labels and TextBoxes for Cyber 9-Line fields
$fields = @(
    "1. DTG of Incident:", "2. Unit POC Information:", "3. Incident Description:",
    "4. Network Type:", "5. Terrain of Incident:", "6. Detection Method:",
    "7. Impact to Mission:", "8. Actions Taken:", "9. Additional Information:"
)

$textBoxes = @()
$yPos = 20

foreach ($field in $fields) {
    $label = New-Object System.Windows.Forms.Label
    $label.Text = $field
    $label.Location = New-Object System.Drawing.Point(10, $yPos)
    $label.Size = New-Object System.Drawing.Size(200, 20)
    $form.Controls.Add($label)

    $textBox = New-Object System.Windows.Forms.TextBox
    $textBox.Location = New-Object System.Drawing.Point(220, $yPos)
    $textBox.Size = New-Object System.Drawing.Size(250, 20)
    $form.Controls.Add($textBox)
    $textBoxes += $textBox

    $yPos += 40
}

# Submit Button
$submitButton = New-Object System.Windows.Forms.Button
$submitButton.Text = "Generate Report"
$submitButton.Location = New-Object System.Drawing.Point(200, $yPos)
$submitButton.Size = New-Object System.Drawing.Size(100, 30)
$submitButton.Add_Click({
    $report = "Cyber 9-Line Report:`n`n"
    for ($i = 0; $i -lt $fields.Count; $i++) {
        $report += "$($fields[$i]) $($textBoxes[$i].Text)`n"
    }
    
    [System.Windows.Forms.MessageBox]::Show($report, "Generated Report")
})

$form.Controls.Add($submitButton)

# Show Form
$form.ShowDialog()