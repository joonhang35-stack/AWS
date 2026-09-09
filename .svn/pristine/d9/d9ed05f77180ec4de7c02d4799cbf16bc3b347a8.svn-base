package com.bcs.zsg.feedback.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.feedback.bo.FeedbackBO;
import com.bcs.zsg.feedback.dao.FeedbackDAO;
import com.bcs.zsg.product.dao.TourPackageDAO;
import com.bcs.zsg.product.vo.FeedbackVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackBO feedbackBO;

    @Autowired
    private FeedbackDAO feedbackDAO;
    
    @Autowired
    private TourPackageDAO tourPkgDAO;
    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#getInvoiceForFeedback()
     */

    @Override
    @Transactional
    public List<FeedbackVO> getInvoiceForFeedback() throws BusinessException {
        return getInvoiceForFeedback(null);
    }

    @Override
    @Transactional
    public List<FeedbackVO> getInvoiceForFeedback(Long idTourBooking) throws BusinessException {
        List<FeedbackVO> feedbackList = feedbackDAO.getInvoiceForFeedback(idTourBooking);

        for (FeedbackVO vo : feedbackList) {
            try {
                if (vo.getTourBookingId() != null) {
                    String existingStatus = feedbackDAO.getFeedbackStatus(vo.getTourBookingId(), vo.getInvId(), vo.getCustomerId());
                    if (existingStatus != null) {
                        if (vo.getInvId() != null) {
                            System.out.println("Feedback entry already exists for invoice: " + vo.getInvId() 
                                               + " (booking: " + vo.getTourBookingId() + "), skipping insertion.");
                        } else {
                            System.out.println("Feedback entry already exists for booking: " + vo.getTourBookingId() 
                                               + ", skipping insertion.");
                        }
                        continue;
                    }
                }

                String uuid = UUID.randomUUID().toString();
                vo.setTokenUuid(uuid);
                vo.setFeedbackStatus("P");
                feedbackDAO.insertFeedback(vo);
            } catch (Exception ex) {
                System.out.println("Error processing feedback for invoice: " + vo.getInvId() + " - " + ex.getMessage());
                ex.printStackTrace(System.out);
            }
        }
       
        return feedbackList;
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#insertFeedback(com.bcs.zsg.product.vo.FeedbackVO)
     */
    @Override
    @Transactional
    public void insertFeedback(FeedbackVO feedbackVO) throws BusinessException {
        feedbackBO.insertFeedback(feedbackVO);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#getFeedbackListbyTourDepId(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public List<FeedbackVO> getFeedbackListbyTourDepId(Long idTourDep) throws BusinessException {
        return feedbackBO.getFeedbackListbyTourDepId(idTourDep);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#updateFeedbackStatus(java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional
    public void updateFeedbackStatus(Long idInvoice, String status) throws BusinessException {
        feedbackBO.updateFeedbackStatus(idInvoice, status);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#updateFeedbackStatusByBookingId(java.lang.Long, java.lang.String)
     */
    @Override
    @Transactional
    public void updateFeedbackStatusByBookingId(Long idBooking, String status) throws BusinessException {
        feedbackBO.updateFeedbackStatusByBookingId(idBooking, status);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.service.FeedbackService#getFeedbackStatus(java.lang.Long)
     */
    @Override
    @Transactional(readOnly = true)
    public String getFeedbackStatus(Long idBooking) throws BusinessException {
        return feedbackBO.getFeedbackStatus(idBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackVO> getPendingFeedbackList() throws BusinessException {
        return feedbackBO.getPendingFeedbackList();
    }

    @Override
    @Transactional
    public void updateFeedbackForEmailSent(Long idTourBooking, Long customerId, String tokenUuid, String status, String updBy) throws BusinessException {
        feedbackBO.updateFeedbackForEmailSent(idTourBooking, customerId, tokenUuid, status, updBy);
    }

    @Override
    @Transactional(readOnly = true)
    public TourDepartureVO getFeedbackReportHeader(Long idTourDep) throws BusinessException {
        return feedbackDAO.getFeedbackReportHeader(idTourDep);
    }

    @Override
    @Transactional
    public void insertFeedbackCustAnswer(Long idFeedback, Long idQuestion, Long idAnswer, String answerText, String remarks) throws BusinessException {
        feedbackBO.insertFeedbackCustAnswer(idFeedback, idQuestion, idAnswer, answerText, remarks);
    }

    @Override
    @Transactional
    public void updateFeedbackComments(Long idFeedback, String comments, String attachment) throws BusinessException {
        feedbackBO.updateFeedbackComments(idFeedback, comments, attachment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> getActiveQuestionsAndAnswers() throws BusinessException {
        return feedbackBO.getActiveQuestionsAndAnswers();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, String> getFeedbackLookupMap() throws BusinessException {
        return feedbackBO.getFeedbackLookupMap();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getFeedbackIdByBookingId(Long idBooking) throws BusinessException {
        return feedbackDAO.getFeedbackIdByBookingId(idBooking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackVO> getFeedbackDetails(Long idFeedback) throws BusinessException {
        return feedbackBO.getFeedbackDetails(idFeedback);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackVO> getAttachmentsByFeedbackId(Long idFeedback) throws BusinessException {
        return feedbackBO.getAttachmentsByFeedbackId(idFeedback);
    }

    @Override
    @Transactional
    public void saveFeedbackEdits(Long idFeedback, String commentsHos, List<FeedbackVO> hosAttachments, List<Long> deletedAttachmentIds) throws BusinessException {
        feedbackBO.saveFeedbackEdits(idFeedback, commentsHos, hosAttachments, deletedAttachmentIds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackVO> getBookingsWithoutFeedback(Long idTourDep) throws BusinessException {
        return feedbackDAO.getBookingsWithoutFeedback(idTourDep);
    }
}