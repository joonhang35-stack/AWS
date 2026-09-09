<!--Paragraph-->
<table class="row" style="width:100%; padding-left: 15px; padding-right: 30px;">
<tbody>
<tr>
<td>
	<!--Message-->
	<table class="row" style="width:100%; font-family: Helvetica, Arial, sans-serif">
		<tbody>
			<tr>
				<td style="font-size:16px;padding-bottom: 10px;" colspan="3">
					<br/>Dear ${customerName},
					<br/>Greeting From Apple Vacations Sdn Bhd!
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					RE
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${re}
				</td>
			</tr>
			<#if bookingNo?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						Booking No.
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${bookingNo}
					</td>
				</tr>
			</#if>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					${docTypeDesc} No.
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${prefix} ${code}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					${docTypeDesc} Status
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${invoiceStatus}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Tour Code
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${tourCode}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Sales Person
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${salesPerson}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Amount
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					RM ${amount}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Administrative Fee
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					RM ${adminCharges}
				</td>
			</tr>
			<tr>
				<td style="font-size:15px; padding-bottom: 10px;width:25%">
					Amount Payable
				</td>
				<td style="font-size:15px; padding-bottom: 10px;">:</td>
				<td style="font-size:15px; padding-bottom: 10px; color: red">
					<b>RM ${totalPaymentAmt}</b>
				</td>
			</tr>
			<tr>
				<td style="font-size:15px; font-family: Helvetica, Arial, sans-serif;padding-bottom: 10px;" align="justify" colspan="3">
					<br/><a href="${url}" style="border: 0 solid #2199e8; border-radius: 3px; color: white;background-color: #7c51a1; display: inline-block; font-family: Helvetica, Arial, sans-serif; font-size: 16px; font-weight: bold; line-height: 1.3; margin: 0; padding: 8px 16px 8px 16px; text-align: left; text-decoration: none;">Make Payment</a></td>
				</td>
			</tr>
		</tbody>
	</table>
	<!--/Message-->

</td>
</tr>
</tbody>
</table>
<!--/Paragraph-->
