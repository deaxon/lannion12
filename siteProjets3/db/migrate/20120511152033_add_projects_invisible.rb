class AddProjectsInvisible < ActiveRecord::Migration
  def self.up
    add_column :projects, :invisible, :boolean
  end

  def self.down
    remove_column :projects, :invisible
  end
end
