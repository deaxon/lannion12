class Project < ActiveRecord::Base
  attr_accessible :description, :name, :title, :urlFile, :urlSite, :image

  mount_uploader :image, FileUploader

end
