class PdfUpload < ActiveRecord::Base
  attr_accessible :pdf

  mount_uploader :pdf, PdfUploader

  belongs_to :attachable, :polymorphic => true
end
