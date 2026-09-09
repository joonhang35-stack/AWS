package com.bcs.zsg.apiresponse.vo;

import java.util.List;

public class CRMRedeemCatalogVO {

	private String status;
    private String timestamp;
    private String message;
    private Integer totalCount;
    private List<CrmCatalogCategory> categories;
    private List<CRMVoucherVO> flatTypes;
    private Integer upstreamStatus;
    private String upstreamUrl;

    public List<CRMVoucherVO> getFlatTypes() {
		return flatTypes;
	}

	public void setFlatTypes(List<CRMVoucherVO> flatTypes) {
		this.flatTypes = flatTypes;
	}

	public Integer getUpstreamStatus() {
        return upstreamStatus;
    }

    public void setUpstreamStatus(Integer upstreamStatus) {
        this.upstreamStatus = upstreamStatus;
    }

    public String getUpstreamUrl() {
        return upstreamUrl;
    }

    public void setUpstreamUrl(String upstreamUrl) {
        this.upstreamUrl = upstreamUrl;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<CrmCatalogCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<CrmCatalogCategory> categories) {
		this.categories = categories;
	}

	public static class CrmCatalogCategory {

        private String name;
        private String categoryType;
        private String description;
        private List<CRMVoucherVO> types;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(String categoryType) {
            this.categoryType = categoryType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

		public List<CRMVoucherVO> getTypes() {
			return types;
		}

		public void setTypes(List<CRMVoucherVO> types) {
			this.types = types;
		}
	}
}