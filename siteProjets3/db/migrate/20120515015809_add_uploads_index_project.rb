class AddUploadsIndexProject < ActiveRecord::Migration
  def self.up
    add_column :uploads, :project_id, :integer
  end

  def self.down
    remove_column :uploads, :project_id
  end
end
