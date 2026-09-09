package com.bcs.zsg.feedback.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.feedback.dao.FeedbackDAO;
import com.bcs.zsg.feedback.service.FeedbackService;
import com.bcs.zsg.product.vo.FeedbackVO;
import com.bcs.zsg.product.vo.TourDepartureVO;

public class FeedbackBOImpl implements FeedbackBO {

    @Autowired
    private FeedbackDAO feedbackDAO;
    
    @Autowired
    private FeedbackService feedbackService;

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#getInvoiceForFeedback()
     */
    @Override
    public List<FeedbackVO> getInvoiceForFeedback() throws BusinessException {
        return getInvoiceForFeedback(null);
    }

    @Override
    public List<FeedbackVO> getInvoiceForFeedback(Long idTourBooking) throws BusinessException {
        return feedbackService.getInvoiceForFeedback(idTourBooking);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#insertFeedback(com.bcs.zsg.product.vo.FeedbackVO)
     */
    @Override
    public void insertFeedback(FeedbackVO feedbackVO) throws BusinessException {
        feedbackDAO.insertFeedback(feedbackVO);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#getFeedbackListbyTourDepId(java.lang.Long)
     */
    @Override
    public List<FeedbackVO> getFeedbackListbyTourDepId(Long idTourDep) throws BusinessException {
        return feedbackDAO.getFeedbackListbyTourDepId(idTourDep);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#updateFeedbackStatus(java.lang.Long, java.lang.String)
     */
    @Override
    public void updateFeedbackStatus(Long idInvoice, String status) throws BusinessException {
        feedbackDAO.updateFeedbackStatus(idInvoice, status);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#updateFeedbackStatusByBookingId(java.lang.Long, java.lang.String)
     */
    @Override
    public void updateFeedbackStatusByBookingId(Long idBooking, String status) throws BusinessException {
        feedbackDAO.updateFeedbackStatusByBookingId(idBooking, status);
    }

    /*
     * (non-Javadoc)
     * @see com.bcs.zsg.product.bo.FeedbackBO#getFeedbackStatus(java.lang.Long)
     */
    @Override
    public String getFeedbackStatus(Long idBooking) throws BusinessException {
        return feedbackDAO.getFeedbackStatus(idBooking);
    }

    @Override
    public List<FeedbackVO> getPendingFeedbackList() throws BusinessException {
        return feedbackDAO.getPendingFeedbackList();
    }

    @Override
    public Long getFeedbackIdByBookingId(Long idBooking) throws BusinessException {
        return feedbackDAO.getFeedbackIdByBookingId(idBooking);
    }

    @Override
    public Long getFeedbackIdByBookingIdAndCustomerId(Long idBooking, Long idCustomer) throws BusinessException {
        return feedbackDAO.getFeedbackIdByBookingIdAndCustomerId(idBooking, idCustomer);
    }

    @Override
    public Long getFeedbackId(Long idBooking, Long idCustomer, Long idInvoice) throws BusinessException {
        return feedbackDAO.getFeedbackId(idBooking, idCustomer, idInvoice);
    }

    @Override
    public void updateFeedbackForResend(Long idFeedback, String tokenUuid, String status, String email, Long idInvoice, String psNo, String updBy) throws BusinessException {
        feedbackDAO.updateFeedbackForResend(idFeedback, tokenUuid, status, email, idInvoice, psNo, updBy);
    }

    @Override
    public TourDepartureVO getFeedbackReportHeader(Long idTourDep) throws BusinessException {
        return feedbackService.getFeedbackReportHeader(idTourDep);
    }

    @Override
    public void insertFeedbackCustAnswer(Long idFeedback, Long idQuestion, Long idAnswer, String answerText, String remarks) throws BusinessException {
        feedbackDAO.insertFeedbackCustAnswer(idFeedback, idQuestion, idAnswer, answerText, remarks);
    }

    @Override
    public void updateFeedbackComments(Long idFeedback, String comments, String attachment) throws BusinessException {
        feedbackDAO.updateFeedbackComments(idFeedback, comments, attachment);
    }

    @Override
    public List<Object[]> getActiveQuestionsAndAnswers() throws BusinessException {
        return feedbackDAO.getActiveQuestionsAndAnswers();
    }

    @Override
    public Map<String, String> getFeedbackLookupMap() throws BusinessException {
        return feedbackDAO.getFeedbackLookupMap();
    }

    @Override
    public void updateFeedbackForEmailSent(Long idTourBooking, Long customerId, String tokenUuid, String status, String updBy) throws BusinessException {
        feedbackDAO.updateFeedbackForEmailSent(idTourBooking, customerId, tokenUuid, status, updBy);
    }

    @Override
    public List<FeedbackVO> getAllFeedbacks() throws BusinessException {
        return feedbackDAO.getAllFeedbacks();
    }

    @Override
    public List<FeedbackVO> getFeedbackDetails(Long idFeedback) throws BusinessException {
        return feedbackDAO.getFeedbackDetails(idFeedback);
    }

    @Override
    public List<FeedbackVO> getAttachmentsByFeedbackId(Long idFeedback) throws BusinessException {
        return feedbackDAO.getAttachmentsByFeedbackId(idFeedback);
    }

    @Override
    public void saveFeedbackEdits(Long idFeedback, String commentsHos, List<FeedbackVO> hosAttachments, List<Long> deletedAttachmentIds) throws BusinessException {
        feedbackDAO.saveFeedbackEdits(idFeedback, commentsHos, hosAttachments, deletedAttachmentIds);
    }

    @Override
    public List<FeedbackVO> getBookingsWithoutFeedback(Long idTourDep) throws BusinessException {
        return feedbackDAO.getBookingsWithoutFeedback(idTourDep);
    }

    @Override
    public void saveCustomerFeedback(FeedbackVO vo, List<com.bcs.zsg.product.vo.FeedbackVO.ManualQuestionAnswer> answers, List<FeedbackVO> attachments, List<Long> deletedAttachmentIds) throws BusinessException {
        feedbackDAO.saveCustomerFeedback(vo, answers, attachments, deletedAttachmentIds);
    }

    @Override
    public List<FeedbackVO> getPaxListForFeedback(Long tourBookingId, String invCode, String psNo) throws BusinessException {
        return feedbackDAO.getPaxListForFeedback(tourBookingId, invCode, psNo);
    }

    @Override
    public void updateTourDepLeaderAndGuide(Long idTourDep, Long idTourLeader, Long idTourGuide) throws BusinessException {
        feedbackDAO.updateTourDepLeaderAndGuide(idTourDep, idTourLeader, idTourGuide);
    }
}