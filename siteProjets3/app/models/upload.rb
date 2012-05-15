class Upload < ActiveRecord::Base
  attr_accessible :file, :pdf

  mount_uploader :file, FileUploader
  mount_uploader :pdf, PdfUploader


  belongs_to :attachable
  belongs_to :project
end
