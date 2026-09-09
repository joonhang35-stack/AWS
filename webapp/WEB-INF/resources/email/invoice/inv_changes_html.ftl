<!--Paragraph-->
<table class="row" style="width:100%; padding-left: 15px; padding-right: 30px;">
	<tbody>
	<tr>
	<td>
	
		<!--Message-->
		<table class="row" style="width:100%">
			<tbody>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 10px;" align="justify">
						<br/>Dear PIC,
					</td>
				</tr>
				<tr>
					<td colspan="3" style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 20px;" align="left">
						PBI that this invoice has been changed by certain reason, kindly refer below for more details.
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;width:33%" align="justify">
						Booking ID
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;" align="justify">:</td>
					<td style="font-size:16px; font-family: Helvetica, Arial, sans-serif; font-weight:bold;" align="justify">
						${bookingID}
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;width:33%" align="justify">
						Invoice No.
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;" align="justify">:</td>
					<td style="font-size:16px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px; font-weight:bold;" align="justify">
						${prefix} ${code}
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;width:33%" align="justify">
						Tour Code
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;" align="justify">:</td>
					<td style="font-size:16px; font-family: Helvetica, Arial, sans-serif; font-weight:bold;" align="justify">
						${tourCode}
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;width:33%" align="justify">
						Tour Package
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;" align="justify">:</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif; vertical-align:top;" align="justify">
						${tourPkg}
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;width:33%" align="justify">
						Departure Date
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;" align="justify">:</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;" align="justify">
						${tourDepDt}
					</td>
				</tr>
				<tr>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;vertical-align:top;width:33%" align="justify">
						Requester
					</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;" align="justify">:</td>
					<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;" align="justify">
						${requester}
					</td>
				</tr>
				<#if info?has_content>
					<tr id="invLabel" class="invLabel">
						<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;vertical-align:top;width:33%" align="justify">
							Changes Made
						</td>
						<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 15px;vertical-align:top;" align="justify">:</td>
						<td id="contentInfo" class="contentInfo" colspan="2" style="font-size:15px; font-family: Helvetica, Arial, sans-serif;font-weight:bold;padding-bottom:15px;color:red;vertical-align:top;" align="justify">
							<#list info?keys as key>
								<pre style="margin:0;font-size:16px; font-family: Helvetica, Arial, sans-serif;font-weight:bold;">${info[key]}</pre>
							</#list>
						</td>
					</tr>
				</#if>
				<#if others?has_content>
					<tr id="invLabel" class="invLabel">
						<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 10px;vertical-align:top;width:33%" align="justify">
							Remarks
						</td>
						<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 10px;vertical-align:top;" align="justify">:</td>
						<td id="contentInfo" class="contentInfo" colspan="2" style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom:10px;vertical-align:top;" align="justify">
							<pre style="margin:0;font-size:15px; font-family: Helvetica, Arial, sans-serif;vertical-align:top;">${others}</pre> 
						</td>
					</tr>
				</#if>
				<!--<tr>
					<td colspan="3" style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 10px;" align="center">
						*** Please take note that this invoice has been changed by certain reason. Thanks and have a great day! ***
					</td>
				</tr>-->
			</tbody>
		</table>
		<!--/Message-->
	
	</td>
	</tr>
	</tbody>
	</table>
	<!--/Paragraph-->
	