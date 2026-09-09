<#--
  FreeMarker inputs:
  - Username / userName: recipient login name
  - userId / loginID: recipient login ID
  - resetLink / url: password-reset URL
-->
          <tr>
            <td class="header-padding" align="center" bgcolor="${bgColor!'#6a4590'}" style="padding:36px 40px 40px; background-color:${bgColor!'#6a4590'};">
              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td align="center" style="padding:0 0 15px;">
                    <img src="${logo!'https://applevacations.my/en/images/logo-white3.png'}" width="120" alt="Apple Vacations" style="display:block; width:120px; max-width:120px; height:auto; font-family:Arial, Helvetica, sans-serif; color:#ffffff; font-size:14px; ${(logoStyle!'')}">
                  </td>
                </tr>
                <tr>
                  <td align="center" style="font-family:Arial, Helvetica, sans-serif; font-size:26px; line-height:32px; font-weight:bold; color:#ffffff; mso-line-height-rule:exactly;">
                    Password Reset
                  </td>
                </tr>
              </table>
            </td>
          </tr>

          <tr>
            <td class="content-padding" style="padding:36px 40px;">
              <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr>
                  <td style="padding:0 0 16px; font-family:Arial, Helvetica, sans-serif; font-size:16px; line-height:26px; color:#222222; mso-line-height-rule:exactly;">
                    Dear <strong>${(Username!userName!'')?html}</strong> (<#if userId?? && userId?has_content>${userId?html}<#else>${(loginID!'loginID')?html}</#if>),
                  </td>
                </tr>
                <tr>
                  <td style="font-family:Arial, Helvetica, sans-serif; font-size:15px; line-height:24px; color:#444444; mso-line-height-rule:exactly;">
                    A request was made to reset/update the password on your ERP/AWS (Apple Web System) account. Please click the button below to proceed.
                  </td>
                </tr>
                <tr>
                  <td align="center" style="padding:30px 0;">
                    <table role="presentation" cellpadding="0" cellspacing="0" border="0" align="center">
                      <tr>
                        <td align="center" bgcolor="#7c51a1" style="background-color:#7c51a1; padding:15px 40px; mso-padding-alt:15px 40px;">
                          <a href="${(resetLink!url!'#')?html}" style="display:inline-block; font-family:Arial, Helvetica, sans-serif; font-size:15px; line-height:18px; font-weight:bold; letter-spacing:0.3px; color:#ffffff; text-decoration:none; mso-line-height-rule:exactly;">
                            <span style="color:#ffffff; text-decoration:none;">RESET PASSWORD</span>
                          </a>
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="center">
                    <table role="presentation" width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#fff9c4" style="width:100%; background-color:#fff9c4;">
                      <tr>
                        <td align="center" style="padding:10px 12px; font-family:Arial, Helvetica, sans-serif; font-size:13px; line-height:20px; color:#666666; mso-line-height-rule:exactly;">
                          <strong>Important:</strong> This link will expire in 24 hours.
                        </td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="center" style="padding:12px 0 0; font-family:Arial, Helvetica, sans-serif; font-size:13px; line-height:20px; color:#888888; mso-line-height-rule:exactly;">
                    If you did not request a password reset, no action is needed, your account remains secure.
                  </td>
                </tr>
              </table>
            </td>
          </tr>

          <tr>
            <td class="footer-padding" align="center" bgcolor="#faf8fb" style="padding:22px 40px; background-color:#faf8fb; border-top:1px solid #f0eaf4; font-family:Arial, Helvetica, sans-serif; font-size:12px; line-height:19px; color:#999999; mso-line-height-rule:exactly;">
              This is an automated message, please do not reply.<br>
              Need help? Please contact IT or ERP support.
            </td>
          </tr>
