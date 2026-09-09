<!--Paragraph-->
<table class="row" style="width:100%; padding-left: 15px; padding-right: 30px;">
<tbody>
<tr>
<td>
	<!--Message-->
	<table class="row" style="width:100%; font-family: Helvetica, Arial, sans-serif">
		<tbody>
			<#if testing?? && testing>
				<tr>
					<td style="
						font-size:14px;
						color:#a94442;
						background-color:#fbeaea;
						border:1px solid #f5c6cb;
						padding:10px;
						margin-bottom:10px;
						margin-top:10px;
					" 
					colspan="3">
						<strong>Test Email Notice</strong><br/>
						This is a test email generated for verification purposes only. Please ignore this message.
					</td>
				</tr>
			</#if>
		
			<tr>
				<td style="font-size:16px;padding-bottom: 10px;" colspan="3">
					<br/>E-Invoice Submission
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Document No.
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${prefixVal} ${code}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Status
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${eInvoiceStatus}
				</td>
			</tr>
			
			<#if reason?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						Reason
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${reason}
					</td>
				</tr>
			</#if>
			
			<#if datetime?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						Time
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${datetime}
					</td>
				</tr>
			</#if>
		</tbody>
	</table>
	<!--/Message-->

</td>
</tr>
</tbody>
</table>
<!--/Paragraph-->
