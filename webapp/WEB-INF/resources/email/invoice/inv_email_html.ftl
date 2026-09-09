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
					<br/>Greetings from ${companyName}. Here is the attached ${lowercaseDocTypeDesc} for your perusal.
				</td>
			</tr>
			<tr>
				<td style="font-size:15px;padding-bottom: 5px; width:25%">
					Package Name
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${re}
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
			<#if invoiceNo?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						${docTypeDesc} No.
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${invoiceNo}
					</td>
				</tr>
			</#if>
			<#if departureDate?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						Departure Date
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${departureDate}
					</td>
				</tr>
			</#if>
			<#if airline?has_content>
				<tr>
					<td style="font-size:15px;padding-bottom: 5px; width:25%">
						Airline
					</td>
					<td style="font-size:15px; padding-bottom: 5px;">:</td>
					<td style="font-size:15px; padding-bottom: 5px;">
						${airline}
					</td>
				</tr>
			</#if>
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
					Payment Status
				</td>
				<td style="font-size:15px; padding-bottom: 5px;">:</td>
				<td style="font-size:15px; padding-bottom: 5px;">
					${invoiceStatus}
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
