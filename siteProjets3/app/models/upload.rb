class Upload < ActiveRecord::Base
  attr_accessible :description, :file

  belongs_to :attachable

  mount_uploader :file, FileUploader
end
