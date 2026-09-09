package com.bcs.zsg.zextra.backend.helper;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.maintenance.service.SystemNumberGenerationService;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class SysNumGenUtil {
	/* Logger instance */
	private final static Logger logger = LoggerFactory.getLogger(SysNumGenUtil.class);
	@Autowired
	private SystemNumberGenerationService sysNumGenService;
	
	/* Manage available instance index */
	private static Queue _freeQueue;
	/* Collection of object instances */
	private static HashMap<String, SystemNumberGenerationVO> _elements;
	/* Thread instance */
	private static SysNumGenThread _sys;
	/* Index instance */
	private static int _idx;
	
	/**
	 * Default construction
	 */
	public SysNumGenUtil() {
		this(1024);
	}
	
	/**
	 * 
	 * @param maxQueueSize
	 */
	public SysNumGenUtil(int maxQueueSize) {
		_freeQueue = new Queue(maxQueueSize);
		_elements = new HashMap<String, SystemNumberGenerationVO>();
		_sys = new SysNumGenThread();
		_sys.start();
	}
	
	/**
	 * Set system number
	 * @param idx - index of the element : session id + sys code (e.g. getSessionInfoBean().getSessionID() + CommonConstant.SYS_NUM_CD_INVC)
	 * @param vo - reset some value (code, company id)
	 * @throws QueueException
	 */
	public static synchronized String getIdx(SystemNumberGenerationVO vo) throws QueueException {
		// set index
		_idx++;
		String idx = _idx + vo.getCode() + vo.getIdCompany();
		
		// set vo to elements
		_elements.put(idx, vo);
		_freeQueue.enqueue(idx);
		_sys.doProcess();
		return idx;
	}
	
	/**
	 * Get system number
	 * @param idx - index of the element : session id + sys code (e.g. getSessionInfoBean().getSessionID() + CommonConstant.SYS_NUM_CD_INVC)
	 * @return
	 */
	public static synchronized SystemNumberGenerationVO getSysNumber(String idx) throws QueueException {
		SystemNumberGenerationVO vo = _elements.get(idx);
		// if not yet get next number
		while (!vo.isGotNextNumber()) {
			vo = _elements.get(idx);
		}
		// remove data from element
		_elements.remove(idx);
		return vo;
	}
	
	/**
	 * Process to get system generator number
	 */
	class SysNumGenThread extends Thread {
		
		/**
		 * Notify this process thread
		 */
		public synchronized void doProcess() {
			notify();
		}
		
		/* Main executeable method
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			while (true) {
				try {
					if (_freeQueue.isEmpty()) {
						_idx = 0;
						timewait();
					}
					if (_freeQueue.isEmpty()) {
						logger.debug("[SysNumGenUtil.SysNumGenThread] run() - unsync");
						continue;
					}
					
					String idx = (String) _freeQueue.dequeue();
					SystemNumberGenerationVO vo = _elements.get(idx);
					vo = sysNumGenService.getSysNumGen(vo);
					_elements.put(idx, vo);
					
				} catch (Exception e) {
					logger.error("[SysNumGenUtil.SysNumGenThread] error - ", e);
				}
			}
		}
		
		/**
		 * Wait forever until notify call
		 */
		private void timewait() {
			timewait(-1);
		}
		
		/**
		 * Wait for given time, If given time is -1, waiting forever until notify call
		 * @param millisecond
		 */
		private void timewait(int millisecond) {
			try {
				synchronized (this) {
					if (millisecond == -1) wait();
					else wait(millisecond);
				}
			} catch (Exception e) {}
		}
	}
}
