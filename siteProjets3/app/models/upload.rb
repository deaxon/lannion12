class Upload < ActiveRecord::Base
  attr_accessible :file

  mount_uploader :file, FileUploader

  belongs_to :attachable, :polymorphic => true
end
